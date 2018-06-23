package com.logiticks.diamondsale.rest.service;

import com.logiticks.diamondsale.rest.model.CustomerModelClass;
import com.logiticks.diamondsale.rest.model.DiamondModelClass;
import com.logiticks.diamondsale.rest.model.MerchantModelClass;
import com.logiticks.diamondsale.rest.model.PlaceOrderModelClass;
import com.logiticks.diamondsale.rest.model.TransactionModelClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by Naveen on 05-06-2018.
 */

public interface ApiService {

    @GET("/api/Diamond")
    Call<List<DiamondModelClass>> getDiamondList();

    @POST("/api/Diamond")
    Call<DiamondModelClass> createDiamond(@Body DiamondModelClass diamond);

    @GET("/api/Customer")
    Call<List<CustomerModelClass>> getCustomer();

    @POST("/api/Customer")
    Call<CustomerModelClass> createCustomer(@Body CustomerModelClass customer);

    @GET("/api/Order")
    Call<List<PlaceOrderModelClass>> getInvoices();

    @POST("/api/PlaceOrder")
    Call<PlaceOrderModelClass> createInvoice(@Body PlaceOrderModelClass invoice);

    @GET("/api/Merchant")
    Call<List<MerchantModelClass>> getMerchants();

    @GET("/api/Merchant/{id}")
    Call<MerchantModelClass> getMerchantById(@Path("id") String id);


    @GET("/api/system/historian")
    Call<List<TransactionModelClass>> getTransactions();

}
