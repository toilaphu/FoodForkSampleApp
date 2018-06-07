package com.phunguyen.foodforksampleapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.phunguyen.foodforksampleapp.BaseActivity;
import com.phunguyen.foodforksampleapp.R;
import com.phunguyen.foodforksampleapp.SearchResultsActivity;

public class SearchActivity extends BaseActivity {

    private EditText inputSearchEdt;
    private Button searchButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        inputSearchEdt = findViewById(R.id.ingredients);
        searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = inputSearchEdt.getText().toString().trim();
                if (query.isEmpty()) {
                    Snackbar.make(searchButton, getString(R.string.search_query_required), Snackbar
                            .LENGTH_LONG).show();
                } else {
                    startActivity(SearchResultsActivity.searchResultsIntent(SearchActivity.this, query));
                }
            }
        });
    }
}
