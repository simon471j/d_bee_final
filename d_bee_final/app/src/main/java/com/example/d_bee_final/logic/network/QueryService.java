package com.example.d_bee_final.logic.network;

import com.example.d_bee_final.logic.bean.ArticleBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface QueryService {
    @FormUrlEncoded
    @POST("article/query/{page}/json")
    Call<ArticleBean> searchArticle(@Path("page") int page, @Field("k") String key);
}
