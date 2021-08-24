package com.example.d_bee_final.ui.mine_page.my_sharing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.d_bee_final.logic.bean.SharedArticleBean;

public class SharedArticleViewModel extends ViewModel {
    final   LiveData<PagedList<SharedArticleBean.ShareArticles.Datas>> sharedArticlePagedList;

    public SharedArticleViewModel() {
        PagedList.Config config = (new PagedList.Config.Builder()).setPageSize(SharedArticleDataSource.PER_PAGE).setPrefetchDistance(3).setInitialLoadSizeHint(SharedArticleDataSource.PER_PAGE * 2).setMaxSize(65536 * SharedArticleDataSource.PER_PAGE).build();
        sharedArticlePagedList = (new LivePagedListBuilder<>(new SharedArticleSourceFactory(), config)).build();
    }

}
