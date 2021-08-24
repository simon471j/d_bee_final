package com.example.d_bee_final.ui.mine_page;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.d_bee_final.logic.Repository;
import com.example.d_bee_final.logic.bean.MinePageInfoBean;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MinePageInfoViewModel extends ViewModel {
    private final MutableLiveData<MinePageInfoBean> minePageInfoBeanMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<MinePageInfoBean> getMinePageInfoBeanMutableLiveData() {
        return minePageInfoBeanMutableLiveData;
    }

    public void refreshMinePageInfo() {
        Repository.getMinePageInfo().enqueue(new Callback<MinePageInfoBean>() {
            @Override
            public void onResponse(Call<MinePageInfoBean> call, Response<MinePageInfoBean> response) {
                Log.d(getClass().getName(), "onResponse: "+response.body());
                if (response.isSuccessful())
                    minePageInfoBeanMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MinePageInfoBean> call, Throwable t) {
                Log.d(getClass().getName(), "onFailure: " + t);
            }
        });
    }


}
