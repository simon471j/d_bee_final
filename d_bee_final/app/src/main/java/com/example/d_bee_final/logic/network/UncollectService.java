package com.example.d_bee_final.logic.network;

import com.example.d_bee_final.logic.bean.UncollectBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UncollectService {
    @POST("lg/uncollect_originId/{originId}/json")
    Call<UncollectBean> uncollect(@Path("originId") int originId);
}
