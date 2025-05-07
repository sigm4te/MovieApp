package com.example.movieapp.di.search_page;

import com.example.movieapp.di.search_result.SearchResultSubcomponent;
import com.example.movieapp.mvp.presenter.search_page.SearchPagePresenter;

import dagger.Subcomponent;

@SearchPageScope
@Subcomponent(modules = SearchPageModule.class)
public interface SearchPageSubcomponent {

    SearchResultSubcomponent searchResultSubcomponent();

    void inject(SearchPagePresenter searchPagePresenter);
}
