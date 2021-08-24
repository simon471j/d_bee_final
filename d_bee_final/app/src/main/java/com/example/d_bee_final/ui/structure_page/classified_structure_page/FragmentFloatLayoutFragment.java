package com.example.d_bee_final.ui.structure_page.classified_structure_page;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.d_bee_final.R;
import com.example.d_bee_final.logic.bean.StructureBean;
import com.example.d_bee_final.ui.structure_page.StructureViewModel;
import com.example.d_bee_final.ui.structure_page.classified_structure_page.StructureArticleActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class FragmentFloatLayoutFragment extends Fragment {

    private StructureViewModel structureViewModel;
    private LinearLayout linearLayoutFloatLayout;


    public FragmentFloatLayoutFragment() {
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
        View view = inflater.inflate(R.layout.fragment_float_layout, container, false);
        structureViewModel = new ViewModelProvider(this).get(StructureViewModel.class);
        initView(view);
        return view;
    }

    private void initView(View view) {
        linearLayoutFloatLayout = view.findViewById(R.id.ll_float_layout_for_structure);
        structureViewModel.getStructureMutableLiveData().observe(getViewLifecycleOwner(), new Observer<StructureBean>() {
            @Override
            public void onChanged(StructureBean structureBean) {
                initChipGroup(structureBean);

            }
        });
        structureViewModel.getStructure();
    }

    private void initChipGroup(StructureBean structureBean) {
        for (StructureBean.Data data : structureBean.getData()) {
            TextView textView = new TextView(getContext());
            textView.setText(data.getName());
//            给chip添加分类
            linearLayoutFloatLayout.addView(textView);
            ChipGroup chipGroup = new ChipGroup(requireContext());
//            添加组
            for (StructureBean.Data.Children child : data.getChildren()) {
                Chip chip = new Chip(requireContext());
                chip.setText(child.getName());
                chipGroup.addView(chip);
//                给chip注册点击事件
                onChipClick(chip, data, child.getId());
            }
//                加入到视图中
            linearLayoutFloatLayout.addView(chipGroup);
        }
    }

    private void onChipClick(Chip chip, StructureBean.Data data, int currentId) {
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("classified_data", data);
                bundle.putInt("current_id", currentId);
                Intent intent = new Intent(requireContext(), StructureArticleActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }
}
