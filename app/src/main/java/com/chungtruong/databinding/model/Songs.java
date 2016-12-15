package com.chungtruong.databinding.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.chungtruong.databinding.realm.RealmDataBinding;

import java.util.List;

import io.realm.annotations.Ignore;

/**
 * Created by chung.truong on 12/13/2016.
 */

public class Songs implements Observable, RealmDataBinding {


    private List<Song> songs;

    @Bindable
    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
        notifyChange();
    }

    public boolean isNullOrEmpty() {
        return songs == null || songs.isEmpty();
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
}