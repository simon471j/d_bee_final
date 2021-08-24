package com.example.d_bee_final.ui.mine_page.my_sharing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.d_bee_final.R;
import com.example.d_bee_final.logic.bean.SharedArticleBean;
import com.example.d_bee_final.ui.mine_page.my_sharing.share_article.ShareArticleActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MySharingActivity extends AppCompatActivity {

    private DeleteArticleViewModel deleteArticleViewModel;
    private SharedArticleListAdapter sharedArticleListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sharing);
        hideActionBar();
        deleteArticleViewModel = new ViewModelProvider(this).get(DeleteArticleViewModel.class);

        initView();
        getArticle();
    }


    @Override
    protected void onRestart() {
        sharedArticleListAdapter.getCurrentList().getDataSource().invalidate();
        Toast.makeText(this, "加载中", Toast.LENGTH_SHORT).show();
        super.onRestart();
    }


    private void getArticle() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_my_sharing);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        SharedArticleViewModel sharedArticleViewModel = new ViewModelProvider(this).get(SharedArticleViewModel.class);
        sharedArticleListAdapter = new SharedArticleListAdapter(this, deleteArticleViewModel, sharedArticleViewModel);
        sharedArticleViewModel.sharedArticlePagedList.observe(this, new Observer<PagedList<SharedArticleBean.ShareArticles.Datas>>() {
            @Override
            public void onChanged(PagedList<SharedArticleBean.ShareArticles.Datas> datas) {
                sharedArticleListAdapter.submitList(datas);
            }
        });
        recyclerView.setAdapter(sharedArticleListAdapter);
    }

    private void hideActionBar() {
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    private void initView() {
        ImageView back = findViewById(R.id.iv_back_arrow_my_sharing_activity);
        ImageView addShare = findViewById(R.id.iv_share_article);
        onAddShareClick(addShare);
        onBackClick(back);

    }

    private void onAddShareClick(ImageView addShare) {
        addShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MySharingActivity.this, ShareArticleActivity.class));
            }
        });
    }


    private void onBackClick(ImageView back) {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}