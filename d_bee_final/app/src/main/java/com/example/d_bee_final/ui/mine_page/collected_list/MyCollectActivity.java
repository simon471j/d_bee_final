package com.example.d_bee_final.ui.mine_page.collected_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.d_bee_final.MyApp;
import com.example.d_bee_final.R;
import com.example.d_bee_final.logic.bean.CollectListBean;
import com.example.d_bee_final.ui.home_page.article.CollectViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class MyCollectActivity extends AppCompatActivity {
    private CollectViewModel collectViewModel;
    private CollectedListViewModel collectedListViewModel;
    private boolean over = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        hideActionBar();
        collectViewModel = new ViewModelProvider(this).get(CollectViewModel.class);
        collectedListViewModel = new ViewModelProvider(this).get(CollectedListViewModel.class);
        initView();

    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_my_collect);
        MyCollectArticleRecyclerViewAdapter homePageArticleRecyclerViewAdapter = new MyCollectArticleRecyclerViewAdapter(new ArrayList<>(), this, collectViewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(homePageArticleRecyclerViewAdapter);
        collectedListViewModel.getCollectListMutableLiveData().observe(this, new Observer<CollectListBean>() {
            @Override
            public void onChanged(CollectListBean collectListBean) {
                homePageArticleRecyclerViewAdapter.setArticleList(collectListBean.getData().getDatas());
                if (collectListBean.getData().getOver()) {
                    over = true;
                }
            }
        });
        collectedListViewModel.refreshCollectedList();
        onRecyclerViewScroll(recyclerView);
    }

    private void onRecyclerViewScroll(RecyclerView recyclerView) {
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
                        collectedListViewModel.refreshCollectedList();
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

    private void hideActionBar() {
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}