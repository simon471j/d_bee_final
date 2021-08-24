package com.example.d_bee_final;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.example.d_bee_final.ui.mine_page.collected_list.CollectedListViewModel;

public class MyApp extends Application {

    @SuppressLint("StaticFieldLeak")
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }
}
