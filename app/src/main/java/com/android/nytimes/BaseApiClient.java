package com.android.nytimes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * BaseApiClient is Retrofit initialization Singleton pattern used
 */
class BaseApiClient {

    private static BaseApiClient mServiceManager;

    /**
     * Singleton reference for BaseApiClient
     * @return BaseApiClient
     */
    static BaseApiClient getInstance() {
        if (mServiceManager == null)
            mServiceManager = new BaseApiClient();
        return mServiceManager;
    }

    Retrofit getClient(String url) {
        return getBuilder(url).build();
    }

    private Retrofit.Builder getBuilder(String url) {
        Gson gson = new GsonBuilder().setLenient().disableHtmlEscaping().create();
        return new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create(gson));
    }

}
