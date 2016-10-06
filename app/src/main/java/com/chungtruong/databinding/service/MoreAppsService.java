package com.chungtruong.databinding.service;


import com.chungtruong.databinding.model.MoreApps;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by chung.truong on 10/4/2016.
 */

public interface MoreAppsService {

    String SERVICE_ENDPOINT = "https://sites.google.com/site/nwanvu/source/";

    @GET("moreapps")
    Observable<MoreApps> getMoreApps();
}
