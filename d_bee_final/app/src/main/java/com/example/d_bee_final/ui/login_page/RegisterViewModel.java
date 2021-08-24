package com.example.d_bee_final.ui.login_page;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.d_bee_final.MyApp;
import com.example.d_bee_final.logic.Repository;
import com.example.d_bee_final.logic.bean.LoginBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {
    private final MutableLiveData<LoginBean> registerMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<LoginBean> getRegisterMutableLiveData() {
        return registerMutableLiveData;
    }

    public void register(String username, String password, String repassword) {
        Repository.getRegisterResult(username, password, repassword).enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                if (response.body() != null) {
                    registerMutableLiveData.setValue(response.body());
                    Toast.makeText(MyApp.context, "注册成功", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MyApp.context, response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                Log.d(getClass().getName(), "onFailure: " + t);
            }
        });

    }
}
