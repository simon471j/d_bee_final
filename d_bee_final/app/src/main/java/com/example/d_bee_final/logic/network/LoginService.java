package com.example.d_bee_final.logic.network;

import com.example.d_bee_final.logic.bean.LoginBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {
    @FormUrlEncoded
    @POST("user/login")
    Call<LoginBean> login(@Field("username") String username, @Field("password") String password);


    @FormUrlEncoded
    @POST("user/register")
    Call<LoginBean> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);
}

