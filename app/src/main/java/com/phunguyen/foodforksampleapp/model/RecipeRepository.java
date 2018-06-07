package com.phunguyen.foodforksampleapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Collections.emptyList;

public class RecipeRepository {

    private SharedPreferences sharedPreferences;
    private Gson gson = new Gson();

    RecipeRepository(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public static RecipeRepository getRecipeRepository(Context context){
        return new RecipeRepository(context.getSharedPreferences("Favorites",Context.MODE_PRIVATE));
    }

    public void addFavorite(Recipe item) {
        List<Recipe> favorites = getFavoriteRecipes();
        favorites.add(item);
        saveFavorites(favorites);
    }

    public void removeFavorite(Recipe item) {
        List<Recipe> favorites = getFavoriteRecipes();
        favorites.remove(item);
        saveFavorites(favorites);
    }

    public void saveFavorites(List<Recipe> favorites) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Const.FAVORITES_KEY, gson.toJson(favorites));
        editor.apply();
    }

    public List<Recipe> getFavoriteRecipes(){
        String favoritesString = sharedPreferences.getString(Const.FAVORITES_KEY, null);
        if (favoritesString != null) {
            return new Gson().fromJson(favoritesString, new TypeToken<List<Recipe>>(){}.getType());
        }
        return new ArrayList<Recipe>();
    }

    public void getRecipes(String query,final RepositoryCallback<List<Recipe>> callback){
        NetworkUtils.getRetrofitClient(Const.BASE_URL).create(RecipeApi.class).search(query).enqueue(new Callback<RecipesContainer>() {
            @Override
            public void onResponse(Call<RecipesContainer> call, Response<RecipesContainer> response) {
                if (response.isSuccessful()) {
                    List<Recipe> recipesContainer = response.body().getRecipes();
                    markFavorites(recipesContainer);
                    callback.onSuccess(recipesContainer);
                } else {
                    callback.onError();
                }
            }

            @Override
            public void onFailure(Call<RecipesContainer> call, Throwable t) {
                callback.onError();
            }
        });
    }

    private void markFavorites(List<Recipe> recipesContainer) {
        if (recipesContainer != null) {
            List<Recipe> favoriteRecipes = getFavoriteRecipes();
            if (favoriteRecipes!=null && !favoriteRecipes.isEmpty()) {
                for (Recipe item: recipesContainer) {
                    for(Recipe recipe:favoriteRecipes){
                        if(recipe.getRecipeId().endsWith(item.getRecipeId())){
                            item.setFavorited(true);
                        }
                    }
                }
            }
        }
    }

    interface RepositoryCallback<T>{
        void onSuccess(T t);
        void onError();
    }

}
