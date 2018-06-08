package com.phunguyen.foodforksampleapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.phunguyen.foodforksampleapp.utils.Const;
import com.phunguyen.foodforksampleapp.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeActivity extends BaseActivity {

    public static Intent recipeIntent(Context context, String query) {
        Intent intent = new Intent(context, RecipeActivity.class);
        intent.putExtra(Const.EXTRA_URL, query);
        return intent;
    }

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);
        final String sourceUrl = getIntent().getStringExtra(Const.EXTRA_URL);
        openRecipe(sourceUrl);
    }

    private void openRecipe(String sourceUrl) {
        if (sourceUrl == null || sourceUrl.isEmpty() || webView == null) {
            return;
        }

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.loadUrl(sourceUrl);
        webView.setWebViewClient(new WebViewClient());
    }

}
