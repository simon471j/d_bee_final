package com.example.d_bee_final.ui.structure_page;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.d_bee_final.logic.Repository;
import com.example.d_bee_final.logic.bean.StructureBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StructureViewModel extends ViewModel {
    private final MutableLiveData<StructureBean> structureMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<StructureBean> getStructureMutableLiveData() {
        return structureMutableLiveData;
    }

    public void getStructure() {
        Repository.getStructure().enqueue(new Callback<StructureBean>() {
            @Override
            public void onResponse(Call<StructureBean> call, Response<StructureBean> response) {
                if (response.isSuccessful())
                    structureMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<StructureBean> call, Throwable t) {
                Log.d(getClass().getName(), "onFailure: "+t);
            }
        });
    }
}
