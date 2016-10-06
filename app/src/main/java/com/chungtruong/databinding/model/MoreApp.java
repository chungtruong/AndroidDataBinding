package com.chungtruong.databinding.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.chungtruong.databinding.BR;

/**
 * Created by chung.truong on 10/4/2016.
 */

public class MoreApp extends BaseObservable {

    private String appname;
    private String appauthor;
    private String appicon;

    @Bindable
    public String getAppicon() {
        return appicon;
    }

    @Bindable
    public String getAppname() {
        return appname;
    }

    @Bindable
    public String getAppauthor() {
        return appauthor;
    }

    public void setAppauthor(String appauthor) {
        this.appauthor = appauthor;
        notifyPropertyChanged(com.chungtruong.databinding.BR.appauthor);
    }

    public void setAppicon(String appicon) {
        this.appicon = appicon;
        notifyPropertyChanged(com.chungtruong.databinding.BR.appicon);
    }

    public void setAppname(String appname) {
        this.appname = appname;
        notifyPropertyChanged(BR.appname);
    }
}
