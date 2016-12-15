package com.chungtruong.databinding.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.android.databinding.library.baseAdapters.BR;
import com.chungtruong.databinding.realm.RealmDataBinding;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by chung.truong on 12/13/2016.
 */

public class Song extends RealmObject implements Observable, RealmDataBinding {

    private int id;
    private String title;

    @Bindable
    public int getId() {
        return id;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Ignore
    private transient PropertyChangeRegistry mCallbacks;

    @Override
    public synchronized void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        if (mCallbacks == null) {
            mCallbacks = new PropertyChangeRegistry();
        }
        mCallbacks.add(onPropertyChangedCallback);
    }

    @Override
    public synchronized void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        if (mCallbacks != null) {
            mCallbacks.remove(onPropertyChangedCallback);
        }
    }

    @Override
    public synchronized void notifyChange() {
        if (mCallbacks != null) {
            mCallbacks.notifyCallbacks(this, 0, null);
        }
    }

    public void notifyPropertyChanged(int fieldId) {
        if (mCallbacks != null) {
            mCallbacks.notifyCallbacks(this, fieldId, null);
        }
    }
}
