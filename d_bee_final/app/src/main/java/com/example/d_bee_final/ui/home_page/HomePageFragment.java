package com.example.d_bee_final.ui.home_page;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.d_bee_final.R;
import com.example.d_bee_final.ui.home_page.article.ArticleFragment;
import com.example.d_bee_final.ui.home_page.article.HomePageArticleViewModel;
import com.example.d_bee_final.ui.home_page.search.SearchActivity;

public class HomePageFragment extends Fragment {

    private NestedScrollView nestedScrollView;
    private HomePageArticleViewModel homePageArticleViewModel;
    private ImageView imageViewSearch;

    public HomePageFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        initView(view);
        return view;
    }


    //          初始化View
    private void initView(View view) {
        homePageArticleViewModel = new ViewModelProvider(this).get(HomePageArticleViewModel.class);
        nestedScrollView = view.findViewById(R.id.nested_scroll_view);
        imageViewSearch = view.findViewById(R.id.iv_search_home_page);

//        搜索按键的点击事件
        setOnSearchClick(imageViewSearch);

//        当nestedScrollView滑动到底部的时候通知liveData进行刷新 从而使ArticleFragment刷新
        onNestedScrollViewScrolled(nestedScrollView);

    }

    private void onNestedScrollViewScrolled(NestedScrollView nestedScrollView) {
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    homePageArticleViewModel.refreshArticle();
                    ArticleFragment.toast.show();
                }
            }
        });
    }

    private void setOnSearchClick(ImageView imageViewSearch) {
        imageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}