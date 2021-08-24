package com.example.d_bee_final.ui.cancel_collect;

import com.example.d_bee_final.logic.Repository;
import com.example.d_bee_final.logic.bean.UncollectBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UncollectViewModel   {
    public static void unCollect(int oringinId) {
        Repository.uncollect(oringinId).enqueue(new Callback<UncollectBean>() {
            @Override
            public void onResponse(Call<UncollectBean> call, Response<UncollectBean> response) {

            }

            @Override
            public void onFailure(Call<UncollectBean> call, Throwable t) {

            }
        });
    }
}
