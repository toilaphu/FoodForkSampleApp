package com.phunguyen.foodforksampleapp;

import com.phunguyen.foodforksampleapp.model.RecipesContainer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeApi {

    // 1 - Get your Food2Fork API key from http://food2fork.com/about/api
    // 2 - Create a keystore.properties file with the following content (including the quotes):
    //     FOOD2FORK_API_KEY="YOUR API KEY"
    @GET("search?key=" + BuildConfig.FOOD2FORK_API_KEY)
    Call<RecipesContainer> search(@Query("q") String query);

}
