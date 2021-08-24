package com.example.d_bee_final.ui.login_page;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.d_bee_final.logic.Repository;
import com.example.d_bee_final.logic.bean.LoginBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    private final MutableLiveData<LoginBean> loginMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<LoginBean> getLoginMutableLiveData() {
        return loginMutableLiveData;
    }

    public void login(String username, String password) {
        Repository.getLoginResult(username, password).enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                if (response.body() != null)
                    loginMutableLiveData.setValue(response.body());
                else try {
                    throw new Exception("登录请求的结果为空");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                Log.d(getClass().getName(), "onFailure: " + t);
            }
        });

    }
}
