package com.chungtruong.databinding.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.List;

/**
 * Created by chung.truong on 10/4/2016.
 */

public class MoreApps extends BaseObservable {

    private List<MoreApp> apps;

    private boolean refreshing;

    private boolean visibleProgressBar;

    @Bindable
    public List<MoreApp> getApps() {
        return apps;
    }

    public void setApps(List<MoreApp> apps) {
        this.apps = apps;
        notifyChange();
    }

    public boolean isNotEmpty() {
        return apps != null && apps.size() > 0;
    }

    @Bindable
    public boolean isRefreshing() {
        return refreshing;
    }

    public void setRefreshing(boolean refreshing) {
        this.refreshing = refreshing;
        notifyPropertyChanged(com.chungtruong.databinding.BR.refreshing);
    }

    @Bindable
    public boolean isVisibleProgressBar() {
        return visibleProgressBar;
    }

    public void setVisibleProgressBar(boolean visibleProgressBar) {
        this.visibleProgressBar = visibleProgressBar;
        notifyPropertyChanged(com.chungtruong.databinding.BR.visibleProgressBar);
    }
}
