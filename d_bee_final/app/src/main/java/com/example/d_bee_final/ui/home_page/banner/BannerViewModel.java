package com.example.d_bee_final.ui.home_page.banner;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.d_bee_final.logic.Repository;
import com.example.d_bee_final.logic.bean.BannerBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * banner的viewModel
 */
public class BannerViewModel extends ViewModel {

    private final MutableLiveData<BannerBean> bannerBeanMutableLiveData = new MutableLiveData<>();


    public MutableLiveData<BannerBean> getBannerBeanMutableLiveData() {
        return bannerBeanMutableLiveData;
    }

    public void refreshBanner() {
        Repository.getBanner().enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                Log.d(getClass().getName(), "onResponse: " + response.body());
                bannerBeanMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {
                Log.d(getClass().getName(), "onFailure: " + t);
            }
        });
    }
}
