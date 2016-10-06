package com.chungtruong.databinding.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.chungtruong.databinding.R;
import com.chungtruong.databinding.databinding.ActivitySimpleBinding;
import com.chungtruong.databinding.viewmodel.SimpleViewModel;

/**
 * Created by chung.truong on 10/5/2016.
 */

public class SimpleActivity extends AppCompatActivity {

    private ActivitySimpleBinding binding;
    private SimpleViewModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_simple);
        model = new SimpleViewModel();
        binding.setSimpleViewModel(model);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.unbind();
        model.onDestroy();
    }
}
