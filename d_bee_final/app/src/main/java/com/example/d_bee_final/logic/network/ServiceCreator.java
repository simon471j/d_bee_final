package com.example.d_bee_final.logic.network;

import com.example.d_bee_final.logic.util.CookieForClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 生成网络的工具
 */
public class ServiceCreator {
    public final static String BASE_URL = "https://www.wanandroid.com";
    private static final CookieForClient cookieForClient = new CookieForClient();
    private final static Retrofit retrofit =
            new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(cookieForClient.getAddInterceptor())
                    .client(cookieForClient.getReceiveInterceptor())
                    .addConverterFactory(GsonConverterFactory.create()).build();

    public static <T> T create(Class<T> clz) {
        return retrofit.create(clz);
    }
}
