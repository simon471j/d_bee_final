package com.example.d_bee_final.logic.network;

import com.example.d_bee_final.logic.bean.DeleteBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DeleteService {
    @POST("lg/user_article/delete/{articleId}/json")
    Call<DeleteBean> delete(@Path("articleId") int id);
}
