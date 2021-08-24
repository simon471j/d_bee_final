package com.example.d_bee_final.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.d_bee_final.R;
import com.example.d_bee_final.ui.home_page.HomePageFragment;
import com.example.d_bee_final.ui.mine_page.MineFragment;
import com.example.d_bee_final.ui.q_a_page.QAFragment;
import com.example.d_bee_final.ui.structure_page.StructureFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
//        隐藏action bar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.hide();

        viewPager = findViewById(R.id.activity_main_view_pager);
//        加载当前fragment最近的三个fragment
        viewPager.setOffscreenPageLimit(3);
        bottomNavigationView = findViewById(R.id.bottom_nav_view);

//        添加fragment集合
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomePageFragment());
        fragmentList.add(new QAFragment());
        fragmentList.add(new StructureFragment());
        fragmentList.add(new MineFragment());

//        ViewPager的适配器
        MyFragmentPagerAdapter viewPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(viewPagerAdapter);

//        bottomNavigationView和ViewPager的联动
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home_page:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_q_and_a:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_structure:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.menu_mine:
                        viewPager.setCurrentItem(3);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}