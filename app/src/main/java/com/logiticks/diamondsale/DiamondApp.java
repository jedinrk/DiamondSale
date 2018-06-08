package com.logiticks.diamondsale;

import android.app.Application;

import com.logiticks.diamondsale.rest.RestClient;

/**
 * Created by Naveen on 06-06-2018.
 */

public class DiamondApp extends Application {

    private static RestClient restClient;

    @Override
    public void onCreate()
    {
        super.onCreate();

        restClient = new RestClient();
    }

    public static RestClient getRestClient()
    {
        return restClient;
    }
}
