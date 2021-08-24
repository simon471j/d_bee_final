package com.example.d_bee_final.logic.network;

import com.example.d_bee_final.logic.bean.StructureBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StructureService {
    @GET("tree/json")
    Call<StructureBean> getStructure();
}
