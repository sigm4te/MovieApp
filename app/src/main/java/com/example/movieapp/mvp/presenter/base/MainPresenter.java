package com.example.movieapp.mvp.presenter.base;

import com.example.movieapp.mvp.view.base.IMainView;
import com.example.movieapp.navigation.Screens;
import com.example.movieapp.utils.log.Logger;
import com.github.terrakok.cicerone.Router;

import moxy.MvpPresenter;

public class MainPresenter extends MvpPresenter<IMainView> {

    private final Router router;

    public MainPresenter (Router router) {
        super();
        Logger.logV(null);
        this.router = router;
    }

    @Override
    protected void onFirstViewAttach() {
        Logger.logV(null);
        super.onFirstViewAttach();
        router.replaceScreen(new Screens.SearchScreen());
    }

    public void backClicked() {
        Logger.logV(null);
        router.exit();
    }
}
