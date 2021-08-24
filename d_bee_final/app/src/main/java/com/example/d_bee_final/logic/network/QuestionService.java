package com.example.d_bee_final.logic.network;

import com.example.d_bee_final.logic.bean.QuestionBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface QuestionService {
    @GET("wenda/list/{page}/json ")
    Call<QuestionBean> getQuestion(@Path("page") int page);
}
