package com.logiticks.diamondsale.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logiticks.diamondsale.rest.service.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Naveen on 05-06-2018.
 */

public class RestClient {

    private static final String BASE_URL = "http://173.193.122.209:31090";
    private ApiService apiService;

    private static Retrofit retrofit;


    public RestClient()
    {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .create();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        /*RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .build();*/

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService()
    {
        return apiService;
    }
}

