package com.example.d_bee_final.ui.mine_page.my_sharing;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.d_bee_final.logic.bean.SharedArticleBean;

import org.jetbrains.annotations.NotNull;

public class SharedArticleSourceFactory extends DataSource.Factory<Integer, SharedArticleBean.ShareArticles.Datas> {
    private MutableLiveData<SharedArticleDataSource> sharedArticleDataSourceMutableLiveData = new MutableLiveData<>();

    @NonNull
    @NotNull
    @Override
    public DataSource<Integer, SharedArticleBean.ShareArticles.Datas> create() {
        SharedArticleDataSource sharedArticleDataSource = new SharedArticleDataSource();
        sharedArticleDataSourceMutableLiveData.postValue(sharedArticleDataSource);
        return sharedArticleDataSource;
    }
}
