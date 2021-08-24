package com.example.d_bee_final.logic.network;

import com.example.d_bee_final.logic.bean.CollectListBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CollectListService {
    @GET("lg/collect/list/{page}/json")
    Call<CollectListBean> getCollectedList(@Path("page") int page);
}
