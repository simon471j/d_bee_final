package com.example.d_bee_final.logic.network;

import com.example.d_bee_final.logic.bean.SharedArticleBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SharedArticleService {
    @GET("user/lg/private_articles/{page}/json")
    Call<SharedArticleBean> getSharedArticle(@Path("page") int page, @Query("page_size") int pageSize);
}
