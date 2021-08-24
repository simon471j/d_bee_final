package com.example.d_bee_final.ui.home_page.article;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.d_bee_final.MyApp;
import com.example.d_bee_final.R;
import com.example.d_bee_final.logic.bean.ArticleBean;

import java.util.ArrayList;
import java.util.List;


public class ArticleFragment extends Fragment {
    private RecyclerView recyclerView;
    private HomePageArticleViewModel homePageArticleViewModel;
    private final List<ArticleBean.Data.Datas> articleList = new ArrayList<>();
    public static Toast toast = Toast.makeText(MyApp.context, "内容加载中 :)", Toast.LENGTH_LONG);
    private CollectViewModel collectViewModel;
    private boolean hasMore = false;

    public ArticleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        homePageArticleViewModel = new ViewModelProvider(this).get(HomePageArticleViewModel.class);
        collectViewModel = new ViewModelProvider(this).get(CollectViewModel.class);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.home_page_article_recycler_view);
        HomePageArticleRecyclerViewAdapter homePageArticleRecyclerViewAdapter = new HomePageArticleRecyclerViewAdapter(articleList, getActivity(), collectViewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(homePageArticleRecyclerViewAdapter);


//      观察网络数据变换
        homePageArticleViewModel.getHomePageArticleBeanMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ArticleBean>() {
            @Override
            public void onChanged(ArticleBean articleBean) {
                homePageArticleRecyclerViewAdapter.setArticleList(articleBean.getData().getDatas());
                toast.cancel();
                if (articleBean.getData().getOver()) {
                    Toast.makeText(requireContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                }
            }
        });
        homePageArticleViewModel.refreshArticle();
        toast.show();
    }
}