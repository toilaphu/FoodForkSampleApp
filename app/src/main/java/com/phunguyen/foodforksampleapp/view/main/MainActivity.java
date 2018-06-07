package com.phunguyen.foodforksampleapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.phunguyen.foodforksampleapp.BaseActivity;
import com.phunguyen.foodforksampleapp.Extension;
import com.phunguyen.foodforksampleapp.FavoritesActivity;
import com.phunguyen.foodforksampleapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.searchButton)
    public void openSearchScreen(){
        Extension.startScreen(this,SearchActivity.class);
    }

    @OnClick(R.id.favButton)
            public void openFavoritesScreen(){
        Extension.startScreen(this,FavoritesActivity.class);
    }

}
