package com.example.movieapp.di.search;

import com.example.movieapp.di.movie.MovieSubcomponent;
import com.example.movieapp.mvp.presenter.search_page.SearchPagePresenter;
import com.example.movieapp.mvp.presenter.search_result.SearchResultPresenter;

import dagger.Subcomponent;

@SearchScope
@Subcomponent(modules = SearchModule.class)
public interface SearchSubcomponent {

    MovieSubcomponent movieSubcomponent();

    void inject(SearchPagePresenter searchPagePresenter);
    void inject(SearchResultPresenter searchResultPresenter);
}
