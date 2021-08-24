package com.example.d_bee_final.logic.network;

import com.example.d_bee_final.logic.bean.ReceiveCoinBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ReceiveCoinService {
    @GET("lg/coin/list/1/json")
    Call<ReceiveCoinBean> getCoinHistory();
}
