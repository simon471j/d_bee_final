package com.example.d_bee_final.ui.structure_page.navi_page;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.d_bee_final.logic.Repository;
import com.example.d_bee_final.logic.bean.NavigationBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NavigationViewModel extends ViewModel {
    private MutableLiveData<NavigationBean> navigationMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<NavigationBean> getNavigationMutableLiveData() {
        return navigationMutableLiveData;
    }

    public void getNavigation() {
        Repository.getNavigation().enqueue(new Callback<NavigationBean>() {
            @Override
            public void onResponse(Call<NavigationBean> call, Response<NavigationBean> response) {
                if (response.isSuccessful())
                    navigationMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NavigationBean> call, Throwable t) {
                Log.d(getClass().getName(), "onFailure: " + t);
            }
        });
    }
}
