package com.example.d_bee_final.ui.mine_page.my_sharing;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.d_bee_final.logic.Repository;
import com.example.d_bee_final.logic.bean.DeleteBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteArticleViewModel extends ViewModel {
    private final MutableLiveData<DeleteBean> deleteBeanMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<DeleteBean> getDeleteBeanMutableLiveData() {
        return deleteBeanMutableLiveData;
    }

    /**
     * @param articleId 文章id
     */
    public void delete(int articleId) {
        Repository.deleteBeanCall(articleId).enqueue(new Callback<DeleteBean>() {
            @Override
            public void onResponse(Call<DeleteBean> call, Response<DeleteBean> response) {
                deleteBeanMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DeleteBean> call, Throwable t) {
                Log.d(getClass().getName(), "onFailure: " + t);
            }
        });
    }
}
