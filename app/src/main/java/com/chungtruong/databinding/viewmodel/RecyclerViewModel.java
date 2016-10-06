package com.chungtruong.databinding.viewmodel;

import android.databinding.BindingAdapter;
import android.support.annotation.VisibleForTesting;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chungtruong.databinding.adapter.RecyclerListAdapter;
import com.chungtruong.databinding.model.MoreApp;
import com.chungtruong.databinding.model.MoreApps;
import com.chungtruong.databinding.service.MoreAppsService;
import com.chungtruong.databinding.service.ServiceFactory;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chung.truong on 10/4/2016.
 */

public class RecyclerViewModel {

    private GetDataListener getDataListener;
    private Subscription subscription;
    public MoreApps moreApps;

    public RecyclerViewModel(GetDataListener getDataListener) {
        this.getDataListener = getDataListener;
        moreApps = new MoreApps();
        moreApps.setVisibleProgressBar(true);
        getMoreApps();
    }

    @VisibleForTesting
    public RecyclerViewModel(MoreApps moreApps, GetDataListener getDataListener) {
        this.moreApps = moreApps;
        moreApps.setVisibleProgressBar(true);
    }

    public void onDestroy() {
        unSubscribeService();
        subscription = null;
        moreApps = null;
        getDataListener = null;
    }

    public void getMoreApps() {
        unSubscribeService();
        MoreAppsService service = ServiceFactory.createService(MoreAppsService.class, MoreAppsService.SERVICE_ENDPOINT);
        subscription = service.getMoreApps()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MoreApps>() {
                    @Override
                    public void onCompleted() {
                        if (getDataListener != null) {
                            getDataListener.onCompleted();
                        }
                        handleLoadingView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getDataListener != null) {
                            getDataListener.onError(e);
                        }
                        handleLoadingView();
                    }

                    @Override
                    public void onNext(MoreApps apps) {
                        if (moreApps != null) {
                            moreApps.setApps(apps.getApps());
                        }
                    }
                });
    }

    private void unSubscribeService() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    private void handleLoadingView() {
        if (moreApps == null) {
            return;
        }
        if (moreApps.isVisibleProgressBar()) {
            moreApps.setVisibleProgressBar(false);
        }
        moreApps.setRefreshing(false);
    }

    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMoreApps();
            }
        };
    }

    @BindingAdapter("app:items")
    public static void bindList(final RecyclerView recyclerView, List<MoreApp> list) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new RecyclerListAdapter(list));
    }

    public interface GetDataListener {
        void onCompleted();

        void onError(Throwable t);
    }
}
