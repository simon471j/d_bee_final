package com.example.d_bee_final.logic.network;

import com.example.d_bee_final.logic.bean.ArticleBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StructureArticleService {
    @GET("article/list/{page}/json")
//    复用HomePageArticleBean
    Call<ArticleBean> getStructureArticle(@Path("page") int page, @Query("cid") int id);
}
