package com.example.d_bee_final.ui.structure_page;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.d_bee_final.R;
import com.example.d_bee_final.ui.structure_page.classified_structure_page.FragmentFloatLayoutFragment;
import com.example.d_bee_final.ui.structure_page.navi_page.NavigationFragment;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StructureFragment extends Fragment {


    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    public StructureFragment() {
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
        View view = inflater.inflate(R.layout.fragment_structure, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        initFragmentListAndTitles();
        ViewPager viewPager = view.findViewById(R.id.view_pager_structure);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout_structure);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @NonNull
            @NotNull
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initFragmentListAndTitles() {
        fragmentList.add(new FragmentFloatLayoutFragment());
        fragmentList.add(new NavigationFragment());
        titles.add("体系");
        titles.add("导航");
    }
}