package com.phunguyen.foodforksampleapp.presenter;

import android.content.Context;

import com.phunguyen.foodforksampleapp.model.Recipe;
import com.phunguyen.foodforksampleapp.model.RecipeRepository;
import com.phunguyen.foodforksampleapp.view.searchresults.SearchResultsView;

import java.util.List;

import javax.inject.Inject;

public class SearchResultsPresenterImpl implements SearchResultsPresenter {

    private SearchResultsView view;
    private RecipeRepository recipeRepository;

    @Override
    public void initialize(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public <T> void attachView(T view) {
        this.view = (SearchResultsView) view;
    }

    @Override
    public void searchRecipes(String query) {
        view.showLoadingView();
        recipeRepository.getRecipes(query, new RecipeRepository.RepositoryCallback<List<Recipe>>() {
            @Override
            public void onSuccess(List<Recipe> recipes) {
                if (recipes == null || recipes.isEmpty()) {
                    view.showEmptyRecipes();
                    return;
                }
                view.showRecipes(recipes);
            }

            @Override
            public void onError() {
                view.showErrorView();
            }
        });
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
        this.view = null;
    }
}
