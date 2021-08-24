package com.example.d_bee_final.logic.network;


import com.example.d_bee_final.logic.bean.CollectBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CollectService {
    @POST("lg/collect/{id}/json")
    Call<CollectBean> collect(@Path("id") int id);
}
