package com.example.d_bee_final.logic.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.d_bee_final.MyApp;

public class UserDao {

    public void saveUserInfo(String userName, String passWord) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString("username", userName);
        editor.putString("password", passWord);
        editor.apply();
    }

    public String getUserName() {
        return getSharedPreferences().getString("username", "unknown_user_name");
    }

    public String getPassWord() {
        return getSharedPreferences().getString("password", "unknown_password");
    }

    public void clearUserInfo() {
        getSharedPreferences().edit().clear().apply();
    }

    public boolean isUserInfoSaved() {
        return getSharedPreferences().contains("username") && getSharedPreferences().contains("password");
    }

    private SharedPreferences getSharedPreferences() {
        return MyApp.context.getSharedPreferences("userAccount", Context.MODE_PRIVATE);
    }
}
