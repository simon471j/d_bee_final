package com.example.d_bee_final.ui.mine_page.my_coin_page;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.d_bee_final.logic.Repository;
import com.example.d_bee_final.logic.bean.ReceiveCoinBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceiveCoinHistoryViewModel extends ViewModel {
    private final MutableLiveData<ReceiveCoinBean> receiveCoinBeanMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<ReceiveCoinBean> getReceiveCoinBeanMutableLiveData() {
        return receiveCoinBeanMutableLiveData;
    }

    public void getReceiveHistory() {
        Repository.receiveCoinBeanCall().enqueue(new Callback<ReceiveCoinBean>() {
            @Override
            public void onResponse(Call<ReceiveCoinBean> call, Response<ReceiveCoinBean> response) {
                if (response.isSuccessful())
                    receiveCoinBeanMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ReceiveCoinBean> call, Throwable t) {
                Log.d(getClass().getName(), "onFailure: " + t);
            }
        });
    }
}
