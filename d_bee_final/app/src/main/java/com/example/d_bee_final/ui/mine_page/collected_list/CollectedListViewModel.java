package com.example.d_bee_final.ui.mine_page.collected_list;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.d_bee_final.logic.Repository;
import com.example.d_bee_final.logic.bean.CollectListBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectedListViewModel extends ViewModel {
    private final MutableLiveData<CollectListBean> collectListMutableLiveData = new MutableLiveData<>();
    private int pageCount = 0;

    public MutableLiveData<CollectListBean> getCollectListMutableLiveData() {
        return collectListMutableLiveData;
    }

    public void refreshCollectedList() {

        Repository.getCollectedListBean(pageCount++).enqueue(new Callback<CollectListBean>() {
            @Override
            public void onResponse(Call<CollectListBean> call, Response<CollectListBean> response) {
                if (response.body() != null) {
                    collectListMutableLiveData.setValue(response.body());
                    Log.d(getClass().getName(), "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<CollectListBean> call, Throwable t) {
                Log.d(getClass().getName(), "onFailure: " + t);
            }
        });
    }
}

