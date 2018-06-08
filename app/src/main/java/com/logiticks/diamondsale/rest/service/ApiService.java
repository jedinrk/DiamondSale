package com.logiticks.diamondsale.rest.service;

import com.logiticks.diamondsale.rest.model.DiamondModelClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by Naveen on 05-06-2018.
 */

public interface ApiService {

    @GET("/api/Diamond")
    Call<List<DiamondModelClass>> getDiamondList();


    @POST("/api/Diamond")
    Call<DiamondModelClass> createDiamond(@Body DiamondModelClass diamond);
}
