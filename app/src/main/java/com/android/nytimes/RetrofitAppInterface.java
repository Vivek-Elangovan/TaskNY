package com.android.nytimes;

import com.android.nytimes.model.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAppInterface {

    @GET("v2/mostviewed/{section}/{period}.json")
    Call<ArticleResponse> getMostPopular(@Path("section") String section, @Path("period") int period, @Query("api-key") String apiKey);
}