package com.example.d_bee_final.logic.dao;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.d_bee_final.MyApp;

import java.util.Set;


public class CookieDao {
    public void saveCookie(Set<String> cookies) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("cookie", cookies);
        editor.apply();
    }

    public Set<String> getCookie() {
        return getSharedPreferences().getStringSet("cookie", null);
    }

    public boolean isCookieSaved() {
        return getCookie() != null;
    }

    public void clearCookie() {
        getSharedPreferences().edit().clear().apply();
    }

    private SharedPreferences getSharedPreferences() {
        return MyApp.context.getSharedPreferences("cookies", Context.MODE_PRIVATE);
    }

}
