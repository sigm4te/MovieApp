package com.example.movieapp.mvp.view.poster_page;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface IPosterPageView extends MvpView {
    void init();
    void setImage(String imageUrl);
    void release();
}
