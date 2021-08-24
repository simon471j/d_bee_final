package com.example.d_bee_final.ui.mine_page.my_sharing.share_article;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.d_bee_final.MyApp;
import com.example.d_bee_final.logic.Repository;
import com.example.d_bee_final.logic.bean.UncollectBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SharingArticleViewModel extends ViewModel {
    private MutableLiveData<UncollectBean> sharedArticleBeanMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<UncollectBean> getSharedArticleBeanMutableLiveData() {
        return sharedArticleBeanMutableLiveData;
    }

    public void share(String title, String link) {
        Repository.sharingArticleBeanCall(title, link).enqueue(new Callback<UncollectBean>() {
            @Override
            public void onResponse(Call<UncollectBean> call, Response<UncollectBean> response) {
                if (response.isSuccessful() && response.body().getErrorCode() == 0) {
                    sharedArticleBeanMutableLiveData.setValue(response.body());
                    Toast.makeText(MyApp.context, "分享成功", Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(MyApp.context, response.body().getErrorMsg(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UncollectBean> call, Throwable t) {
                Log.d(getClass().getName(), "onFailure: " + t);
            }
        });
    }
}
