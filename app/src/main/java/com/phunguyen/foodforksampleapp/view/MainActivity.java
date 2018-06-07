package com.phunguyen.foodforksampleapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.searchButton) LinearLayout searchButton;
    @BindView(R.id.favButton) LinearLayout favButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        searchButton.setOnClickListener(this);
//        favButton.setOnClickListener(this);
    }

    @OnClick(R.id.searchButton)
    public void openSearchScreen(){
        Extension.startScreen(this,SearchActivity.class);
    }

    @OnClick(R.id.favButton)
            public void openFavoritesScreen(){
        Extension.startScreen(this,FavoritesActivity.class);
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.searchButton:
//                Extension.startScreen(this,SearchActivity.class);
//                break;
//            case R.id.favButton:
//                Extension.startScreen(this,FavoritesActivity.class);
//                break;
//            default:
//                break;
//        }
//    }
}
