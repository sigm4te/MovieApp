package com.example.movieapp.mvp.presenter.poster_page;

import com.example.movieapp.app.MovieApp;
import com.example.movieapp.mvp.view.poster_page.IPosterPageView;
import com.example.movieapp.utils.log.Logger;
import com.github.terrakok.cicerone.Router;

import javax.inject.Inject;

import moxy.MvpPresenter;

public class PosterPagePresenter extends MvpPresenter<IPosterPageView> {

    @Inject
    Router router;

    private final String imageUrl;

    public PosterPagePresenter(String imageUrl) {
        this.imageUrl = imageUrl;
        MovieApp.instance.getMovieSubcomponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Logger.logV(null);
        getViewState().init();
        setData();
    }

    private void setData() {
        Logger.logV(null);
        getViewState().setImage(imageUrl);
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
