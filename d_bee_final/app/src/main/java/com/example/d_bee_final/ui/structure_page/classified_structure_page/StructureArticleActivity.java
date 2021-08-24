package com.example.d_bee_final.ui.structure_page.classified_structure_page;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.example.d_bee_final.R;
import com.example.d_bee_final.logic.bean.StructureBean;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StructureArticleActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private StructureBean.Data data;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private int page;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_structure_article);
        initData();
        initView();

    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        data = (StructureBean.Data) bundle.getSerializable("classified_data");
//        被点击chip的id 我们将其设置为tabLayout当前所显示的页面
        int currentId = bundle.getInt("current_id");
//           判断所点击的item的页数在哪里 我们直接可以转到那一页
        int count = 0;
        for (StructureBean.Data.Children child : data.getChildren()) {
            if (currentId != child.getId())
                count++;
            else if (currentId == child.getId())
                page = count;

            titles.add(child.getName());
            fragmentList.add(new StructureArticleFragment(child.getId()));
        }

    }


    private void initView() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.hide();
//        设置页面的title
        toolbarTitle = findViewById(R.id.tv_title_structure_article_classified);
        toolbarTitle.setText(data.getName());
        tabLayout = findViewById(R.id.tab_layout_structure_classified);
        viewPager = findViewById(R.id.view_pager_structure_classified_activity);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @NotNull
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(page);
//        延迟之后才会让tabLayout滑动到对应的tab 否则虽然tab被选中了但是没有滑动过去
        tabLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                tabLayout.selectTab(tabLayout.getTabAt(page));
            }
        }, 100);


    }
}