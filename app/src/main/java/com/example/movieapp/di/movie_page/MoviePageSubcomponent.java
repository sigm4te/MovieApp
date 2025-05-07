package com.example.movieapp.di.movie_page;

import com.example.movieapp.mvp.presenter.movie_page.MoviePagePresenter;

import dagger.Subcomponent;

@MoviePageScope
@Subcomponent(modules = MoviePageModule.class)
public interface MoviePageSubcomponent {

    void inject(MoviePagePresenter moviePagePresenter);
}
