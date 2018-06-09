package com.phunguyen.foodforksampleapp.presenter;

import android.content.Context;

import com.phunguyen.foodforksampleapp.model.Recipe;
import com.phunguyen.foodforksampleapp.model.RecipeRepository;

public interface SearchResultsPresenter extends BasePresenter {

    void initialize(RecipeRepository recipeRepository);

    void searchRecipes(String query);

    void addFavorite(Recipe item);

    void removeFavorite(Recipe item);
}
