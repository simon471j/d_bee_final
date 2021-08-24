package com.example.d_bee_final.ui.mine_page;

import android.content.Intent;
import android.os.Bundle;


import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.d_bee_final.R;
import com.example.d_bee_final.databinding.FragmentMineBinding;
import com.example.d_bee_final.ui.mine_page.collected_list.MyCollectActivity;
import com.example.d_bee_final.ui.mine_page.my_coin_page.MyCoinActivity;
import com.example.d_bee_final.ui.mine_page.my_sharing.MySharingActivity;


public class MineFragment extends Fragment {


    public MineFragment() {
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
        FragmentMineBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false);
        MinePageInfoViewModel minePageInfoViewModel = new ViewModelProvider(requireActivity()).get(MinePageInfoViewModel.class);
        binding.setMinePageInfoViewModel(minePageInfoViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        View view = binding.getRoot();
        initView(binding);
        minePageInfoViewModel.refreshMinePageInfo();
        return view;
    }

    private void initView(FragmentMineBinding binding) {
        binding.constraintLayoutMyCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), MyCoinActivity.class);
                intent.putExtra("coin_count", Integer.parseInt(binding.tvCoinNum.getText().toString()));
                startActivity(intent);
            }
        });
        binding.constraintLayoutMyCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), MyCollectActivity.class));
            }
        });
        binding.constraintLayoutMySharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), MySharingActivity.class));
            }
        });
    }


}