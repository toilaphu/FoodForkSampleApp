package com.phunguyen.foodforksampleapp.presenter;

import android.content.Context;

import com.phunguyen.foodforksampleapp.model.Recipe;
import com.phunguyen.foodforksampleapp.model.RecipeRepository;

public interface FavoritesPresenter extends BasePresenter {

    void queryFavoriteRecipes(Context context);

    void addFavorite(Recipe item);

    void removeFavorite(Recipe item);
}
