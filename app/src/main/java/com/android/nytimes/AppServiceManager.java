package com.android.nytimes;

import android.support.annotation.NonNull;

import com.android.nytimes.model.ArticleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * AppServiceManager is a Facade Pattern. This layer is the interface between client and server communication
 */
class AppServiceManager {

    static final int MOST_POPULAR_LIST = 1;
    private static AppServiceManager appServiceManager;

    /**
     * Singleton Object reference for AppServiceManager
     *
     * @return appServiceManager AppServiceManager
     */
    static AppServiceManager getInstance() {
        if (appServiceManager == null)
            appServiceManager = new AppServiceManager();
        return appServiceManager;
    }

    /**
     * This method is to get the Most Popular viewed article in NY Times
     *
     * @param viewModel ArticleViewModel
     */
    void getPopularArticle(final ArticleViewModel viewModel, String apiKey) {
        String url = "http://api.nytimes.com/svc/mostpopular/";
        RetrofitAppInterface apiService = BaseApiClient.getInstance().getClient(url).create(RetrofitAppInterface.class);
        Call<ArticleResponse> call = apiService.getMostPopular("all-sections", 7, apiKey);
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(@NonNull Call<ArticleResponse> call, @NonNull Response<ArticleResponse> response) {
                if (response.isSuccessful()) {
                    viewModel.onSyncData(response.body(), MOST_POPULAR_LIST);
                } else {
                    viewModel.onFailed(MOST_POPULAR_LIST, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArticleResponse> call, @NonNull Throwable t) {
                viewModel.onFailed(MOST_POPULAR_LIST, null);

            }
        });
    }
}
