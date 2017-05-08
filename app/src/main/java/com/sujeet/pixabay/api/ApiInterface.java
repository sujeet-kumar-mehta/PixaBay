package com.sujeet.pixabay.api;


import com.sujeet.pixabay.model.ImageModelResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Api Interface
 */
public interface ApiInterface {

    String API_KEY = "5299810-876b1793f7d3d86c98fb02be9";


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("?key="+API_KEY+"&image_type=photo")
    Call<ImageModelResponse> getSearchImages(@Query("q") String imageSearchKeyWord);



}
