package com.example.d_bee_final.logic.network;

import com.example.d_bee_final.logic.bean.MinePageInfoBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MinePageInfoService {
    @GET("lg/coin/userinfo/json")
    Call<MinePageInfoBean> getMinePageInfo();
}
