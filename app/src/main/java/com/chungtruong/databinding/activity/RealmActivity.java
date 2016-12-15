package com.chungtruong.databinding.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.chungtruong.databinding.R;
import com.chungtruong.databinding.fragment.RealmFragment;

/**
 * Created by chung.truong on 12/13/2016.
 */

public class RealmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, new RealmFragment())
                .commit();
    }
}
