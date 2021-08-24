package com.example.d_bee_final.logic.util;

import com.example.d_bee_final.logic.dao.CookieDao;

import java.io.IOException;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * cookie持久化
 */
public class AddCookieInterceptor implements Interceptor {
    private CookieDao cookieDao = new CookieDao();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        Set<String> cookieSet = cookieDao.getCookie();
        if (cookieSet != null) {
            for (String cookie : cookieSet) {
                builder.addHeader("Cookie", cookie);
            }
        }
        return chain.proceed(builder.build());
    }
}

