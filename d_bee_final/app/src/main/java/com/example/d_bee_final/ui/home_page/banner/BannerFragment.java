package com.example.d_bee_final.ui.home_page.banner;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.d_bee_final.R;
import com.example.d_bee_final.logic.bean.BannerBean;

import com.example.d_bee_final.ui.WebActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;


public class BannerFragment extends Fragment {

    private BannerViewModel bannerViewModel;
    private final List<String> bannerUrlList = new ArrayList<>();
    private HomePageBannerAdapter homePageBannerAdapter;


    public BannerFragment() {
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
        View view = inflater.inflate(R.layout.fragment_banner, container, false);
        bannerViewModel = new ViewModelProvider(this).get(BannerViewModel.class);
        initBanner(view);
        return view;
    }

//      初始化banner
    private void initBanner(View view) {
        List<String> imagePathList = new ArrayList<>();
        Banner<String, HomePageBannerAdapter> banner = view.findViewById(R.id.banner);

//        网络请求成功接收到数据之后更新ui
        bannerViewModel.getBannerBeanMutableLiveData().observe(getViewLifecycleOwner(), new Observer<BannerBean>() {
            @Override
            public void onChanged(BannerBean bannerBean) {
                imagePathList.clear();
                bannerUrlList.clear();
                for (BannerBean.Data data : bannerBean.getData()) {
                    imagePathList.add(data.getImagePath());
                    bannerUrlList.add(data.getUrl());
                }
                banner.addBannerLifecycleObserver(getViewLifecycleOwner());
                homePageBannerAdapter = new HomePageBannerAdapter(imagePathList, getContext());
                addOnBannerClick();
                banner.setAdapter(homePageBannerAdapter);
            }
        });
//        刷新banner
        bannerViewModel.refreshBanner();
    }

//    为banner添加点击事件
    private void addOnBannerClick() {
        homePageBannerAdapter.setOnBannerListener(new OnBannerListener<String>() {
            @Override
            public void OnBannerClick(String data, int position) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("url", bannerUrlList.get(position));
                startActivity(intent);
            }
        });
    }
}