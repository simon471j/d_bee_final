package com.example.d_bee_final.ui.home_page.search;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.d_bee_final.logic.Repository;
import com.example.d_bee_final.logic.bean.ArticleBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QueryArticleViewModel extends ViewModel {
    private final MutableLiveData<ArticleBean> articleMutableLiveData = new MutableLiveData<>();
    private int page = 0;

    public MutableLiveData<ArticleBean> getArticleMutableLiveData() {
        return articleMutableLiveData;
    }

    public void getQueriedArticle(String key) {
        Repository.getQueriedArticle(page++, key).enqueue(new Callback<ArticleBean>() {
            @Override
            public void onResponse(Call<ArticleBean> call, Response<ArticleBean> response) {
                if (response.isSuccessful())
                    articleMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArticleBean> call, Throwable t) {
                Log.d(getClass().getName(), "onFailure: " + t);
            }
        });
    }
}
