package com.example.d_bee_final.ui.home_page.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.d_bee_final.ui.home_page.article.CollectViewModel;
import com.example.d_bee_final.ui.home_page.article.HomePageArticleRecyclerViewAdapter;



import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class QueriedArticleFragment extends Fragment {

    private QueryArticleViewModel queryArticleViewModel;
    private CollectViewModel collectViewModel;
    private HomePageArticleRecyclerViewAdapter articleRecyclerViewAdapter;
    private final List<ArticleBean.Data.Datas> articleList = new ArrayList<>();
    private String keyWord;
    private RecyclerView recyclerView;
    private boolean over = false;
    private SearchActivity searchActivity;

    public QueriedArticleFragment() {
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
        View view = inflater.inflate(R.layout.fragment_queried_article, container, false);
        queryArticleViewModel = new ViewModelProvider(this).get(QueryArticleViewModel.class);
        collectViewModel = new ViewModelProvider(this).get(CollectViewModel.class);
        getAssociatedActivity();
        getDataFromBundle();
        initView(view);
        return view;
    }

    private void getAssociatedActivity() {
        searchActivity = (SearchActivity) getActivity();
    }

    private void initSearchBox() {
        searchActivity.getEditTextInput().setText(keyWord);
    }

    private void getDataFromBundle() {
        Bundle arguments = getArguments();
        keyWord = Objects.requireNonNull(arguments).getString("key");


    }

    private void initView(View view) {
        initSearchBox();
        recyclerView = view.findViewById(R.id.recycler_view_queried_fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        articleRecyclerViewAdapter = new HomePageArticleRecyclerViewAdapter(articleList, requireActivity(), collectViewModel);
        recyclerView.setAdapter(articleRecyclerViewAdapter);
        onRecyclerViewRefresh();
        queryArticleViewModel.getArticleMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ArticleBean>() {
            @Override
            public void onChanged(ArticleBean articleBean) {
                over = articleBean.getData().getOver();
                articleRecyclerViewAdapter.setArticleList(articleBean.getData().getDatas());
            }
        });
        queryArticleViewModel.getQueriedArticle(keyWord);

    }

    //    监听recyclerView是否加载到底部 决定加载
    private void onRecyclerViewRefresh() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //屏幕中最后一个可见子项的position
                assert layoutManager != null;
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                //当前屏幕所看到的子项个数
                int visibleItemCount = layoutManager.getChildCount();
                //当前RecyclerView的所有子项个数
                int totalItemCount = layoutManager.getItemCount();
                if (visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1) {
                    if (!over) {
                        queryArticleViewModel.getQueriedArticle(keyWord);
                        Toast.makeText(MyApp.context, "加载中", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MyApp.context, "没有更多数据了噢", Toast.LENGTH_SHORT).show();
                        //移除监听器
                        recyclerView.removeOnScrollListener(this);
                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {

        searchActivity.getEditTextInput().setText("");
        super.onDestroyView();
    }
}