package com.example.movieapp.mvp.presenter.search_page;

import com.example.movieapp.app.MovieApp;
import com.example.movieapp.mvp.presenter.search_page.button.ISearchPageButtonPresenter;
import com.example.movieapp.mvp.view.search_page.ISearchPageView;
import com.example.movieapp.navigation.Screens;
import com.example.movieapp.utils.log.Logger;
import com.github.terrakok.cicerone.Router;

import javax.inject.Inject;

import moxy.MvpPresenter;

public class SearchPagePresenter extends MvpPresenter<ISearchPageView> {

    @Inject
    Router router;

    public SearchPagePresenter() {
        MovieApp.instance.getSearchPageSubcomponent().inject(this);
    }

    private class SearchPageButtonPresenter implements ISearchPageButtonPresenter {

        @Override
        public void onClick(String query) {
            router.navigateTo(new Screens.SearchResultScreen(query));
        }
    }

    @Override
    protected void onFirstViewAttach() {
        Logger.logV(null);
        super.onFirstViewAttach();
        getViewState().init();
    }

    @Override
    public void onDestroy() {
        Logger.logV(null);
        super.onDestroy();
        getViewState().release();
    }

    private final SearchPageButtonPresenter searchButtonPresenter = new SearchPageButtonPresenter();

    public ISearchPageButtonPresenter getPresenter() {
        return searchButtonPresenter;
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
