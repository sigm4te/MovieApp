package com.example.movieapp.mvp.view.movie_page;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface IMoviePageView extends MvpView {
    void init();
    void setData(MovieViewModel movie);
    void release();
}
