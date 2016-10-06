package com.chungtruong.databinding.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chungtruong.databinding.R;
import com.chungtruong.databinding.databinding.FragmentRecyclerBinding;
import com.chungtruong.databinding.viewmodel.RecyclerViewModel;

/**
 * Created by chung.truong on 10/4/2016.
 */

public class RecyclerFragment extends Fragment implements RecyclerViewModel.GetDataListener {

    private static final String TAG = RecyclerFragment.class.getName();
    private FragmentRecyclerBinding binding;
    private RecyclerViewModel model;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        binding = FragmentRecyclerBinding.bind(view);
        model = new RecyclerViewModel(this);
        binding.setRecyclerViewModel(model);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.unbind();
        model.onDestroy();
    }

    @Override
    public void onCompleted() {
        Log.e(TAG, "onCompleted");
    }

    @Override
    public void onError(Throwable t) {
        Log.e(TAG, "onError: " + t.getMessage());
    }
}
