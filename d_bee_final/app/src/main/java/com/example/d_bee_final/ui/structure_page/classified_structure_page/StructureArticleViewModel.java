package com.example.d_bee_final.ui.structure_page.classified_structure_page;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.d_bee_final.logic.Repository;
import com.example.d_bee_final.logic.bean.ArticleBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StructureArticleViewModel extends ViewModel {
    private MutableLiveData<ArticleBean> homePageArticleMutableLiveData = new MutableLiveData<>();
    private int page = 0;

    public MutableLiveData<ArticleBean> getHomePageArticleMutableLiveData() {
        return homePageArticleMutableLiveData;
    }

    public void getStructureArticle(int id) {
        Repository.getStructureArticle(page++, id).enqueue(new Callback<ArticleBean>() {
            @Override
            public void onResponse(Call<ArticleBean> call, Response<ArticleBean> response) {
                if (response.isSuccessful()) {
                    homePageArticleMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArticleBean> call, Throwable t) {
                Log.d(getClass().getName(), "onFailure: "+t);
            }
        });
    }
}
