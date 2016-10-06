package com.chungtruong.databinding;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by chung.truong on 10/5/2016.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
