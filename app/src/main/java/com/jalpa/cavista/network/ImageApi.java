package com.jalpa.cavista.network;

import com.jalpa.cavista.model.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Call the Url to fetch the images.
 */

public interface ImageApi  {
    @GET("{fullUrl}")  @Headers("Authorization:Client-ID 137cda6b5008a7c")
    Call<DataResponse> getImages(@Path(value = "fullUrl", encoded = true) int number , @Query("q") String queryString);
}
