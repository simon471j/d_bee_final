package com.example.d_bee_final.ui.home_page.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.d_bee_final.R;
import com.example.d_bee_final.logic.bean.TrendingWordBean;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class TrendingSearchFragment extends Fragment {

    private TrendingSearchViewModel trendingSearchViewModel;
    private SearchActivity searchActivity;

    public TrendingSearchFragment() {
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
        View view = inflater.inflate(R.layout.fragment_trending_search, container, false);
        trendingSearchViewModel = new ViewModelProvider(this).get(TrendingSearchViewModel.class);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ChipGroup chipGroup = view.findViewById(R.id.chip_group_trending_fragment);
        searchActivity = (SearchActivity) getActivity();
        trendingSearchViewModel.getTrendingWordMutableLiveData().observe(getViewLifecycleOwner(), new Observer<TrendingWordBean>() {
            @Override
            public void onChanged(TrendingWordBean trendingWordBean) {
                for (TrendingWordBean.Data datum : trendingWordBean.getData()) {
                    Chip chip = new Chip(requireContext());
                    chip.setText(datum.getName());
                    chipGroup.addView(chip);
//                    点击chip之后自动搜索
                    setOnChipClick(chip);
                    trendingSearchViewModel.getTrendingWordMutableLiveData().removeObserver(this);
                }
            }
        });
        trendingSearchViewModel.getTrendingWord();
    }

    private void setOnChipClick(Chip chip) {
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchActivity.search(chip.getText().toString());
            }
        });
    }
}