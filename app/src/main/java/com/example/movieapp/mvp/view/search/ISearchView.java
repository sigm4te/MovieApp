package com.example.movieapp.mvp.view.search;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface ISearchView extends MvpView {
    void init();
}
