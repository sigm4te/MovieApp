package com.example.movieapp.mvp.presenter.search;

import com.example.movieapp.mvp.presenter.search.button.ISearchButtonPresenter;
import com.example.movieapp.mvp.view.search.ISearchView;
import com.example.movieapp.utils.log.Logger;
import com.github.terrakok.cicerone.Router;

import moxy.MvpPresenter;

public class SearchPresenter extends MvpPresenter<ISearchView> {

    private final Router router;

    public SearchPresenter(Router router) {
        this.router = router;
    }

    private class SearchButtonPresenter implements ISearchButtonPresenter {

        @Override
        public void onClick(String query) {
            //
        }
    }

    @Override
    protected void onFirstViewAttach() {
        Logger.logV(null);
        super.onFirstViewAttach();
        getViewState().init();
    }

    private final SearchPresenter.SearchButtonPresenter searchButtonPresenter = new SearchPresenter.SearchButtonPresenter();

    public ISearchButtonPresenter getPresenter() {
        return searchButtonPresenter;
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
