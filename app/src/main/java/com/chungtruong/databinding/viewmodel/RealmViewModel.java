package com.chungtruong.databinding.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chungtruong.databinding.adapter.SongAdapter;
import com.chungtruong.databinding.firebase.RxFirebase;
import com.chungtruong.databinding.model.Song;
import com.chungtruong.databinding.model.Songs;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chung.truong on 12/13/2016.
 */

public class RealmViewModel {

    private Context context;
    private Subscription subscription;
    public Songs songList;
    private GetFirebaseDataListener getFirebaseDataListener;
    private Realm realm;

    public RealmViewModel(Context context, GetFirebaseDataListener getFirebaseDataListener) {
        this.context = context;
        this.getFirebaseDataListener = getFirebaseDataListener;
        songList = new Songs();
    }

    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    private void unscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public void onReloadClicked() {
        realm.beginTransaction();
        final RealmResults<Song> songs = realm.where(Song.class).findAll();
        if (!songs.isEmpty()) {
            songs.deleteAllFromRealm();
        }
        realm.commitTransaction();
        songList.setSongs(songs);
        getSongs();
    }

    public void getSongs() {
        final RealmResults<Song> songs = realm.where(Song.class).findAll();
        if (songs != null && songs.size() != 0) {
            List<Song> songList = songs.subList(0, songs.size());
            this.songList.setSongs(songList);
        } else {
            unscribe();
            final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            subscription = RxFirebase.observe(reference)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<DataSnapshot>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(DataSnapshot dataSnapshot) {
                            final Songs songs = dataSnapshot.getValue(Songs.class);
                            songList.setSongs(songs.getSongs());
                            if (getFirebaseDataListener != null) {
                                getFirebaseDataListener.onNext(songs);
                            }
                        }
                    });
        }
    }

    @BindingAdapter("app:bindList")
    public static void bindList(final RecyclerView view, List<Song> list) {
        final SongAdapter adapter = new SongAdapter(list);
        view.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        view.setHasFixedSize(true);
        view.setAdapter(adapter);
    }

    public void onDestroy() {

        songList = null;
        unscribe();
        subscription = null;
        context = null;
        getFirebaseDataListener = null;
    }

    public interface GetFirebaseDataListener {
        void onCompleted();

        void onError(Throwable t);

        void onNext(Songs songs);
    }
}
