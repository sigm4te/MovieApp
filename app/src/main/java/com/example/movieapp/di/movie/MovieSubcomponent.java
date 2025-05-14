package com.example.movieapp.di.movie;

import com.example.movieapp.mvp.presenter.movie_page.MoviePagePresenter;
import com.example.movieapp.mvp.presenter.poster_page.PosterPagePresenter;

import dagger.Subcomponent;

@MovieScope
@Subcomponent(modules = MovieModule.class)
public interface MovieSubcomponent {

    void inject(MoviePagePresenter moviePagePresenter);
    void inject(PosterPagePresenter posterPagePresenter);
}
