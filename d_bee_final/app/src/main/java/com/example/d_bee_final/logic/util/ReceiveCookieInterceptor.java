package com.example.d_bee_final.logic.util;

import com.example.d_bee_final.logic.dao.CookieDao;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 拦截cookie
 */
public class ReceiveCookieInterceptor implements Interceptor {
    private CookieDao cookieDao = new CookieDao();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (!response.headers("Set-Cookie").isEmpty()) {
            HashSet<String> stringHashSet = new HashSet<>(response.headers("Set-Cookie"));
            cookieDao.saveCookie(stringHashSet);
        }
        return response;
    }
}
