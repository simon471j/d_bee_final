package com.example.d_bee_final.logic.network;


import com.example.d_bee_final.logic.bean.UncollectBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SharingArticleService {
    @FormUrlEncoded
    @POST("lg/user_article/add/json")
    Call<UncollectBean> share(@Field("title") String title, @Field("link") String link);
}
