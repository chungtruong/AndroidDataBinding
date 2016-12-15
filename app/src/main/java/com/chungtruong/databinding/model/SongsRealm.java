package com.chungtruong.databinding.model;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by chung.truong on 12/13/2016.
 */

public class SongsRealm extends RealmObject {

    private RealmList<Song> songs;

    public RealmList<Song> getSongs() {
        return songs;
    }

    public void setSongs(RealmList<Song> songs) {
        this.songs = songs;
    }
}
