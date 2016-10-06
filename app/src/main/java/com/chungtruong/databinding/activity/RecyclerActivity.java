package com.chungtruong.databinding.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.chungtruong.databinding.R;
import com.chungtruong.databinding.fragment.RecyclerFragment;

/**
 * Created by chung.truong on 10/5/2016.
 */

public class RecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, new RecyclerFragment())
                .commit();
    }
}
