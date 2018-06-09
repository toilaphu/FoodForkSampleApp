package com.phunguyen.foodforksampleapp.presenter;

import com.phunguyen.foodforksampleapp.view.search.SearchView;

public class SearchPresenterImpl implements SearchPresenter {

    private SearchView view;
    @Override
    public void verifyQuery(String query) {
        if(query.isEmpty()){
            view.displayError();
        }else{
            view.openSearchResultScreen();
        }
    }

    @Override
    public <T> void attachView(T view) {
        this.view = (SearchView) view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
