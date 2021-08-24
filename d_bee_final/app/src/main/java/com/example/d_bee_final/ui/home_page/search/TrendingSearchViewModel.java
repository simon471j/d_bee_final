package com.example.d_bee_final.ui.home_page.search;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.d_bee_final.logic.Repository;
import com.example.d_bee_final.logic.bean.TrendingWordBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendingSearchViewModel extends ViewModel {
    private MutableLiveData<TrendingWordBean> trendingWordMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<TrendingWordBean> getTrendingWordMutableLiveData() {
        return trendingWordMutableLiveData;
    }

    public void getTrendingWord() {
        Repository.getTrendingWord().enqueue(new Callback<TrendingWordBean>() {
            @Override
            public void onResponse(Call<TrendingWordBean> call, Response<TrendingWordBean> response) {
                if (response.isSuccessful())
                    trendingWordMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TrendingWordBean> call, Throwable t) {
                Log.d(getClass().getName(), "onFailure: " + t);
            }
        });
    }
}
