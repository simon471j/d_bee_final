package com.example.d_bee_final.ui.structure_page.navi_page;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.d_bee_final.R;
import com.example.d_bee_final.logic.bean.NavigationBean;
import com.example.d_bee_final.ui.WebActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;


public class NavigationFragment extends Fragment {


    private LinearLayout linearLayout;
    private NavigationViewModel navigationViewModel;

    public NavigationFragment() {
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
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        navigationViewModel = new ViewModelProvider(this).get(NavigationViewModel.class);
        initView(view);
        return view;
    }

    private void initView(View view) {
        linearLayout = view.findViewById(R.id.ll_float_layout_for_navigation);
        navigationViewModel.getNavigationMutableLiveData().observe(getViewLifecycleOwner(), new Observer<NavigationBean>() {
            @Override
            public void onChanged(NavigationBean navigationBean) {
                initFloatLayout(navigationBean.getData());
            }
        });
        navigationViewModel.getNavigation();
    }

    private void initFloatLayout(List<NavigationBean.Data> data) {
        for (NavigationBean.Data datum : data) {
            TextView textView = new TextView(requireContext());
            textView.setText(datum.getName());
            linearLayout.addView(textView);
            ChipGroup chipGroup = new ChipGroup(requireContext());
            for (NavigationBean.Data.Articles article : datum.getArticles()) {
                Chip chip = new Chip(requireContext());
                chip.setText(article.getTitle());
                chipGroup.addView(chip);
                onChipClick(chip, article);
            }
            linearLayout.addView(chipGroup);
        }
    }

    private void onChipClick(Chip chip, NavigationBean.Data.Articles article) {
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), WebActivity.class);
                intent.putExtra("url", article.getLink());
                startActivity(intent);
            }
        });
    }
}