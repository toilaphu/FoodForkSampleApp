package com.phunguyen.foodforksampleapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phunguyen.foodforksampleapp.model.Recipe;
import com.phunguyen.foodforksampleapp.model.RecipeRepository;
import com.phunguyen.foodforksampleapp.utils.Const;

import java.util.List;

public class SearchResultsActivity extends BaseActivity{

    private RecipeRepository recipeRepository;
    private RecyclerView recipesRv;
    private TextView retryTxt;
    private LinearLayout loadingContainer;
    private LinearLayout errorContainer;
    private LinearLayout noresultsContainer;

    public static Intent searchResultsIntent(Context context, String query){
        Intent intent = new Intent(context, SearchResultsActivity.class);
        intent.putExtra(Const.EXTRA_QUERY,query);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final String query = getIntent().getStringExtra(Const.EXTRA_QUERY);
        getSupportActionBar().setSubtitle(query);

        recipeRepository = RecipeRepository.getRecipeRepository(this);

        recipesRv = findViewById(R.id.list);
        retryTxt = findViewById(R.id.retry);
        loadingContainer = findViewById(R.id.loadingContainer);
        errorContainer = findViewById(R.id.errorContainer);
        noresultsContainer = findViewById(R.id.noresultsContainer);

        retryTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchRecipes(query);
            }
        });
        searchRecipes(query);
    }

    public void searchRecipes(String query){
        showLoadingView();
        recipeRepository.getRecipes(query,  new RecipeRepository.RepositoryCallback<List<Recipe>>() {
            @Override
            public void onSuccess(List<Recipe> recipes) {
                if (recipes != null && !recipes.isEmpty()) {
                    showRecipes(recipes);
                } else {
                    showEmptyRecipes();
                }
            }
            @Override
            public void onError() {
                showErrorView();
            }
        });
    }

    public void showLoadingView() {
        loadingContainer.setVisibility(View.VISIBLE);
        errorContainer.setVisibility(View.GONE);
        recipesRv.setVisibility(View.GONE);
        noresultsContainer.setVisibility(View.GONE);
    }

    public void showEmptyRecipes() {
        loadingContainer.setVisibility(View.GONE);
        errorContainer.setVisibility(View.GONE);
        recipesRv.setVisibility(View.VISIBLE);
        noresultsContainer.setVisibility(View.VISIBLE);
    }

    public void showRecipes(final List<Recipe> recipes) {
        loadingContainer.setVisibility(View.GONE);
        errorContainer.setVisibility(View.GONE);
        recipesRv.setVisibility(View.VISIBLE);
        noresultsContainer.setVisibility(View.GONE);
        recipesRv.setLayoutManager(new LinearLayoutManager(this));
        recipesRv.setAdapter(new RecipeAdapter(recipes, new RecipeAdapter.RecipeAdapterListener() {
            @Override
            public void onClickItem(Recipe item) {
                startActivity(RecipeActivity.recipeIntent(SearchResultsActivity.this,item.getSourceUrl()));
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
                recipeRepository.removeFavorite(item);
                recipesRv.getAdapter().notifyItemChanged(recipes.indexOf(item));
            }
        }));
    }

    public void showErrorView() {
        loadingContainer.setVisibility(View.GONE);
        errorContainer.setVisibility(View.VISIBLE);
        recipesRv.setVisibility(View.GONE);
        noresultsContainer.setVisibility(View.GONE);
    }

}
