package com.phunguyen.foodforksampleapp.presenter;

import android.content.Context;

import com.phunguyen.foodforksampleapp.model.Recipe;
import com.phunguyen.foodforksampleapp.model.RecipeRepository;
import com.phunguyen.foodforksampleapp.view.favorites.FavoritesView;

import java.util.List;

import javax.inject.Inject;

public class FavoritesPresenterImpl implements FavoritesPresenter {

    private FavoritesView view;
    @Inject RecipeRepository recipeRepository;

    @Override
    public <T> void attachView(T view) {
        ((FavoritesView) view).getContext().recipeAppComponent.inject(this);
        this.view = (FavoritesView) view;
        recipeRepository.initialize(((FavoritesView) view).getContext());
    }

    @Override
    public void queryFavoriteRecipes(Context context) {
        if (recipeRepository != null) {
            List<Recipe> favoriteRecipes = recipeRepository.getFavoriteRecipes();
            if (favoriteRecipes == null || favoriteRecipes.isEmpty()) {
                view.showEmptyRecipes();
                return;
            }
            view.showRecipes(favoriteRecipes);
        }
    }

    @Override
    public void addFavorite(Recipe item) {
        if (recipeRepository != null) {
            recipeRepository.addFavorite(item);
        }
    }

    @Override
    public void removeFavorite(Recipe item) {
        if (recipeRepository != null) {
            recipeRepository.removeFavorite(item);
        }
    }

    @Override
    public void detachView() {
        view = null;
    }

}
