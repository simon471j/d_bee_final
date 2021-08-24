package com.example.d_bee_final.ui.home_page.article;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.d_bee_final.logic.Repository;
import com.example.d_bee_final.logic.bean.CollectBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectViewModel extends ViewModel {
    private final MutableLiveData<CollectBean> collectMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<CollectBean> getCollectMutableLiveData() {
        return collectMutableLiveData;
    }

    public void collect(int id) {
        Repository.getCollectResult(id).enqueue(new Callback<CollectBean>() {
            @Override
            public void onResponse(Call<CollectBean> call, Response<CollectBean> response) {
                if (response.body() != null) {
                    collectMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CollectBean> call, Throwable t) {

            }
        });
    }
}
