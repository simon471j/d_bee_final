package com.example.d_bee_final.logic.network;

import com.example.d_bee_final.logic.bean.BannerBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BannerService {
    @GET("banner/json")
    Call<BannerBean> getBanner();
}
