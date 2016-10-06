package com.chungtruong.databinding.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.chungtruong.databinding.activity.LoginActivity;
import com.chungtruong.databinding.activity.RecyclerActivity;
import com.chungtruong.databinding.activity.SimpleActivity;

/**
 * Created by chung.truong on 10/5/2016.
 */

public class MainViewModel {

    private Context context;

    public MainViewModel(Context context) {
        this.context = context;
    }

    public void onSimpleDemoClicked(View view) {
        startActivity(SimpleActivity.class);
    }

    public void onLoginDemoClicked(View view) {
        startActivity(LoginActivity.class);
    }

    public void onRecyclerViewDemoClicked(View view) {
        startActivity(RecyclerActivity.class);
    }

    private void startActivity(Class clazz) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }
}
