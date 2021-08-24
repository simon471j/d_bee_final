package com.example.d_bee_final.logic.network;

import com.example.d_bee_final.logic.bean.ArticleBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HomePageArticleService {
    @GET("article/list/{page}/json")
    Call<ArticleBean> getHomePageArticle(@Path("page") int page);
}
