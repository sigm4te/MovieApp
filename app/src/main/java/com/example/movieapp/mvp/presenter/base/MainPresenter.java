package com.example.movieapp.mvp.presenter.base;

import com.example.movieapp.app.MovieApp;
import com.example.movieapp.mvp.view.base.IMainView;
import com.example.movieapp.navigation.Screens;
import com.example.movieapp.utils.log.Logger;
import com.github.terrakok.cicerone.Router;

import javax.inject.Inject;

import moxy.MvpPresenter;

public class MainPresenter extends MvpPresenter<IMainView> {

    @Inject
    Router router;

    public MainPresenter () {
        super();
        Logger.logV(null);
        MovieApp.instance.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        Logger.logV(null);
        super.onFirstViewAttach();
        router.replaceScreen(new Screens.SearchPageScreen());
    }

    public void backClicked() {
        Logger.logV(null);
        router.exit();
    }
}
