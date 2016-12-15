package com.chungtruong.databinding.firebase;

import com.google.firebase.FirebaseException;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.subscriptions.Subscriptions;

/**
 * Created by chung.truong on 10/31/2016.
 */

public class RxFirebase {

    public enum FirebaseEventType {
        CHILD_ADDED, CHILD_CHANGED, CHILD_REMOVED, CHILD_MOVED
    }

    /**
     * Essentially a struct so that we can pass all children events through as a single object.
     */
    public static class FirebaseChildEvent {
        public DataSnapshot snapshot;
        public FirebaseEventType eventType;
        public String prevName;

        public FirebaseChildEvent(DataSnapshot snapshot, FirebaseEventType eventType, String prevName) {
            this.snapshot = snapshot;
            this.eventType = eventType;
            this.prevName = prevName;
        }
    }

    public static Observable<FirebaseChildEvent> observeChildren(final Query ref) {
        return Observable.create(new Observable.OnSubscribe<FirebaseChildEvent>() {

            @Override
            public void call(final Subscriber<? super FirebaseChildEvent> subscriber) {
                final ChildEventListener listener = ref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String prevName) {
                        subscriber.onNext(new FirebaseChildEvent(dataSnapshot, FirebaseEventType.CHILD_ADDED, prevName));
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String prevName) {
                        subscriber.onNext(new FirebaseChildEvent(dataSnapshot, FirebaseEventType.CHILD_CHANGED, prevName));
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        subscriber.onNext(new FirebaseChildEvent(dataSnapshot, FirebaseEventType.CHILD_REMOVED, null));
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String prevName) {
                        subscriber.onNext(new FirebaseChildEvent(dataSnapshot, FirebaseEventType.CHILD_MOVED, prevName));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Turn the FirebaseError into a throwable to conform to the API
                        subscriber.onError(new FirebaseException(databaseError.getMessage()));
                    }

                });

                // When the subscription is cancelled, remove the listener
                subscriber.add(Subscriptions.create(new Action0() {
                    @Override
                    public void call() {
                        ref.removeEventListener(listener);
                    }
                }));
            }
        });
    }

    private static Func1<FirebaseChildEvent, Boolean> makeEventFilter(final FirebaseEventType eventType) {
        return new Func1<FirebaseChildEvent, Boolean>() {

            @Override
            public Boolean call(FirebaseChildEvent firebaseChildEvent) {
                return firebaseChildEvent.eventType == eventType;
            }
        };
    }

    public static Observable<FirebaseChildEvent> observeChildAdded(Query ref) {
        return observeChildren(ref).filter(makeEventFilter(FirebaseEventType.CHILD_ADDED));
    }

    public static Observable<FirebaseChildEvent> observeChildChanged(Query ref) {
        return observeChildren(ref).filter(makeEventFilter(FirebaseEventType.CHILD_CHANGED));
    }

    public static Observable<FirebaseChildEvent> observeChildMoved(Query ref) {
        return observeChildren(ref).filter(makeEventFilter(FirebaseEventType.CHILD_MOVED));
    }

    public static Observable<FirebaseChildEvent> observeChildRemoved(Query ref) {
        return observeChildren(ref).filter(makeEventFilter(FirebaseEventType.CHILD_REMOVED));
    }

    public static Observable<DataSnapshot> observe(final Query ref) {

        return Observable.create(new Observable.OnSubscribe<DataSnapshot>() {

            @Override
            public void call(final Subscriber<? super DataSnapshot> subscriber) {
                final ValueEventListener listener = ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        subscriber.onNext(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Turn the FirebaseError into a throwable to conform to the API
                        subscriber.onError(new FirebaseException(databaseError.getMessage()));
                    }
                });

                // When the subscription is cancelled, remove the listener
                subscriber.add(Subscriptions.create(new Action0() {
                    @Override
                    public void call() {
                        ref.removeEventListener(listener);
                    }
                }));
            }
        });
    }
}