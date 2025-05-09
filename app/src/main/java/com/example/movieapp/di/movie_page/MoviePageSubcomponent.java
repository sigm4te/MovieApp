package com.example.movieapp.di.movie_page;

import com.example.movieapp.mvp.presenter.movie_page.MoviePagePresenter;
import com.example.movieapp.mvp.presenter.poster_page.PosterPagePresenter;

import dagger.Subcomponent;

@MoviePageScope
@Subcomponent(modules = MoviePageModule.class)
public interface MoviePageSubcomponent {

    void inject(MoviePagePresenter moviePagePresenter);
    void inject(PosterPagePresenter posterPagePresenter);
}
