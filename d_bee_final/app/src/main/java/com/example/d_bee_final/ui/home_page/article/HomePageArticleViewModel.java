package com.example.d_bee_final.ui.home_page.article;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.d_bee_final.logic.Repository;
import com.example.d_bee_final.logic.bean.ArticleBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageArticleViewModel extends ViewModel {
//    此处为static是因为ArticleFragment和HomePageFragment都持有对该ViewModel的引用
//    这是为了方便自动加载（HomePageFragment中含有包含ArticleFragment的FragmentContainerView 当NestedScrollerView滑动到底部时调用(因为接口不符合Paging组件的规范)refreshArticle ）
    private final static MutableLiveData<ArticleBean> homePageArticleBeanMutableLiveData = new MutableLiveData<>();
    private final static MutableLiveData<Integer> pageCount = new MutableLiveData<>();

    public MutableLiveData<ArticleBean> getHomePageArticleBeanMutableLiveData() {
        return homePageArticleBeanMutableLiveData;
    }

//    在repository中请求网络数据
    public void refreshArticle() {
        if (pageCount.getValue() == null) {
            pageCount.setValue(0);
        } else
            pageCount.setValue(pageCount.getValue() + 1);
        Repository.getHomePageArticle(pageCount.getValue()).enqueue(new Callback<ArticleBean>() {
            @Override
            public void onResponse(Call<ArticleBean> call, Response<ArticleBean> response) {
                Log.d(getClass().getName(), "onResponse: " + response.body());
                homePageArticleBeanMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArticleBean> call, Throwable t) {
                Log.d(getClass().getName(), "onFailure: " + t);
            }
        });
    }
}
