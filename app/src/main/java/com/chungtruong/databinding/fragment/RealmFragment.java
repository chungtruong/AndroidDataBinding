package com.chungtruong.databinding.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chungtruong.databinding.R;
import com.chungtruong.databinding.databinding.FragmentRealmBinding;
import com.chungtruong.databinding.model.Song;
import com.chungtruong.databinding.model.Songs;
import com.chungtruong.databinding.viewmodel.RealmViewModel;

import io.realm.Realm;

/**
 * Created by chung.truong on 12/13/2016.
 */

public class RealmFragment extends Fragment implements RealmViewModel.GetFirebaseDataListener {

    private FragmentRealmBinding binding;
    private RealmViewModel model;
    private Realm mRealm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_realm, container, false);
        binding = FragmentRealmBinding.bind(view);
        model = new RealmViewModel(getActivity(), this);
        binding.setRealmViewModel(model);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.onReloadClicked();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mRealm = Realm.getDefaultInstance();
        model.setRealm(mRealm);
        model.getSongs();
    }

    @Override
    public void onStop() {
        super.onStop();
        mRealm.close();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        model.onDestroy();
        binding.unbind();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onNext(Songs songs) {
        mRealm.beginTransaction();
        for (Song song : songs.getSongs()) {
            mRealm.insert(song);
        }
        mRealm.commitTransaction();
    }
}
