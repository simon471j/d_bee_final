package com.example.d_bee_final.ui.home_page.banner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.d_bee_final.MyApp;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * banner的适配器
 */
public class HomePageBannerAdapter extends BannerAdapter<String, HomePageBannerAdapter.ViewHolder> {




    public HomePageBannerAdapter(List<String> pathList, Context context) {
        super(pathList);
    }


    @Override
    public HomePageBannerAdapter.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return new ViewHolder(imageView);
    }

    @Override
    public void onBindView(HomePageBannerAdapter.ViewHolder holder, String data, int position, int size) {
        Glide.with(MyApp.context).load(data).into(holder.imageView);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView;
        }
    }
}
