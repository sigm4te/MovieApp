package com.example.movieapp.di.search_result;

import com.example.movieapp.di.movie_page.MoviePageSubcomponent;
import com.example.movieapp.mvp.presenter.search_result.SearchResultPresenter;

import dagger.Subcomponent;

@SearchResultScope
@Subcomponent(modules = SearchResultModule.class)
public interface SearchResultSubcomponent {

    MoviePageSubcomponent moviePageSubcomponent();

    void inject(SearchResultPresenter searchResultPresenter);
}
