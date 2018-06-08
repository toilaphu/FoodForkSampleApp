package com.phunguyen.foodforksampleapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phunguyen.foodforksampleapp.model.Recipe;
import com.phunguyen.foodforksampleapp.model.RecipeRepository;

import java.util.List;

public class FavoritesActivity extends BaseActivity{

    private RecipeRepository recipeRepository;
    private RecyclerView recipesRv;
    private TextView retryTxt;
    private LinearLayout loadingContainer;
    private LinearLayout errorContainer;
    private LinearLayout noresultsContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recipeRepository = RecipeRepository.getRecipeRepository(this);

        recipesRv = findViewById(R.id.list);
        retryTxt = findViewById(R.id.retry);
        loadingContainer = findViewById(R.id.loadingContainer);
        errorContainer = findViewById(R.id.errorContainer);
        noresultsContainer = findViewById(R.id.noresultsContainer);

        List<Recipe> favoriteRecipes = recipeRepository.getFavoriteRecipes();
        if(favoriteRecipes ==null || favoriteRecipes.isEmpty()){
            showEmptyRecipes();
        }else{
            showRecipes(favoriteRecipes);
        }
    }

    private void showEmptyRecipes() {
        loadingContainer.setVisibility(View.GONE);
        errorContainer.setVisibility(View.GONE);
        recipesRv.setVisibility(View.VISIBLE);
        noresultsContainer.setVisibility(View.VISIBLE);
    }

    private void showRecipes(final List<Recipe> recipes) {
        loadingContainer.setVisibility(View.GONE);
        errorContainer.setVisibility(View.GONE);
        recipesRv.setVisibility(View.VISIBLE);
        noresultsContainer.setVisibility(View.GONE);

        recipesRv.setLayoutManager(new LinearLayoutManager(this));
        recipesRv.setAdapter(new RecipeAdapter(recipes, new RecipeAdapter.RecipeAdapterListener() {
            @Override
            public void onClickItem(Recipe item) {
                startActivity(RecipeActivity.recipeIntent(FavoritesActivity.this,item.getSourceUrl()));
            }

            @Override
            public void onAddFavorite(Recipe item) {
                item.setFavorited(true);
                recipeRepository.addFavorite(item);
                recipesRv.getAdapter().notifyItemChanged(recipes.indexOf(item));
            }

            @Override
            public void onRemoveFavorite(Recipe item) {
                item.setFavorited(false);
                recipeRepository.addFavorite(item);
                recipesRv.getAdapter().notifyItemChanged(recipes.indexOf(item));
            }
        }));
    }

}
