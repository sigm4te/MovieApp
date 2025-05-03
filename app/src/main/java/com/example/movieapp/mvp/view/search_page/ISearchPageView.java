package com.example.movieapp.mvp.view.search_page;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface ISearchPageView extends MvpView {
    void init();
}
