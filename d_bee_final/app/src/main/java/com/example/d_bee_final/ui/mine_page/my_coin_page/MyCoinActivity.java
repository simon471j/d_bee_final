package com.example.d_bee_final.ui.mine_page.my_coin_page;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.d_bee_final.R;
import com.example.d_bee_final.databinding.ActivityMyCoinBinding;
import com.example.d_bee_final.logic.bean.ReceiveCoinBean;
import com.example.d_bee_final.ui.mine_page.MinePageInfoViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyCoinActivity extends AppCompatActivity {

    private ReceiveCoinHistoryViewModel receiveCoinHistoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMyCoinBinding activityMyCoinBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_coin);
        receiveCoinHistoryViewModel = new ViewModelProvider(this).get(ReceiveCoinHistoryViewModel.class);
        activityMyCoinBinding.setViewModel(receiveCoinHistoryViewModel);
        activityMyCoinBinding.setLifecycleOwner(this);
        hideActionBar();
        initView(activityMyCoinBinding);
    }

//    初始化页面
    private void initView(ActivityMyCoinBinding activityMyCoinBinding) {
        int coinCount = getIntent().getIntExtra("coin_count", 0);
        activityMyCoinBinding.tvCoinCountMyCoinPage.setText(String.valueOf(coinCount));
        activityMyCoinBinding.recyclerViewMyCoinPage.setLayoutManager(new LinearLayoutManager(this));
        receiveCoinHistoryViewModel.getReceiveHistory();
        onBackClick(activityMyCoinBinding);

    }

//    返回点击事件
    private void onBackClick(ActivityMyCoinBinding activityMyCoinBinding) {
        activityMyCoinBinding.ivBackMyCoinPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

//    隐藏状态栏
    private void hideActionBar() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.hide();
    }

//    dataBinding静态方法
    @BindingAdapter("bindRecyclerView")
    public static void bind(RecyclerView recyclerView, List<ReceiveCoinBean.Data.Datas> datasList) {
        recyclerView.setAdapter(new MyCoinHistoryAdapter(datasList));
    }
}