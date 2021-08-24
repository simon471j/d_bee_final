package com.example.d_bee_final.logic.network;

import com.example.d_bee_final.logic.bean.NavigationBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NavigationService {
    @GET("navi/json")
    Call<NavigationBean> getNavigation();
}
