package com.example.d_bee_final.ui.mine_page.my_sharing;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.d_bee_final.logic.Repository;
import com.example.d_bee_final.logic.bean.SharedArticleBean;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SharedArticleDataSource extends PageKeyedDataSource<Integer, SharedArticleBean.ShareArticles.Datas> {
    public static final int PER_PAGE = 8;
    private static final int FIRST_PAGE = 1;


    @Override
    public void loadInitial(@NonNull @NotNull PageKeyedDataSource.LoadInitialParams<Integer> params, @NonNull @NotNull PageKeyedDataSource.LoadInitialCallback<Integer, SharedArticleBean.ShareArticles.Datas> callback) {
        Repository.sharedArticleBeanCall(FIRST_PAGE, PER_PAGE).enqueue(new Callback<SharedArticleBean>() {
            @Override
            public void onResponse(Call<SharedArticleBean> call, Response<SharedArticleBean> response) {
                if (response.body() != null) {
                    callback.onResult(response.body().getData().getShareArticles().getDatas(), null, FIRST_PAGE + 1);
                }

            }

            @Override
            public void onFailure(Call<SharedArticleBean> call, Throwable t) {
                Log.d(getClass().getName(), "onFailure: " + t);
            }
        });
    }

    @Override
    public void loadBefore(@NonNull @NotNull PageKeyedDataSource.LoadParams<Integer> params, @NonNull @NotNull PageKeyedDataSource.LoadCallback<Integer, SharedArticleBean.ShareArticles.Datas> callback) {

    }

    @Override
    public void loadAfter(@NonNull @NotNull PageKeyedDataSource.LoadParams<Integer> params, @NonNull @NotNull PageKeyedDataSource.LoadCallback<Integer, SharedArticleBean.ShareArticles.Datas> callback) {
        Repository.sharedArticleBeanCall(params.key, PER_PAGE).enqueue(new Callback<SharedArticleBean>() {
            @Override
            public void onResponse(Call<SharedArticleBean> call, Response<SharedArticleBean> response) {
                if (response.body() != null) {
                    Integer nextKey = response.body().getData().getShareArticles().getOver() ? null : params.key + 1;
                    callback.onResult(response.body().getData().getShareArticles().getDatas(), nextKey);
                }
            }

            @Override
            public void onFailure(Call<SharedArticleBean> call, Throwable t) {
                Log.d(getClass().getName(), "onFailure: " + t);
            }
        });
    }
}
