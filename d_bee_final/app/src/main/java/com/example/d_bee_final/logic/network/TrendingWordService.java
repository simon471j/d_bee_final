package com.example.d_bee_final.logic.network;

import com.example.d_bee_final.logic.bean.TrendingWordBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TrendingWordService {
    @GET("hotkey/json")
    Call<TrendingWordBean> getTrendingWord();
}
