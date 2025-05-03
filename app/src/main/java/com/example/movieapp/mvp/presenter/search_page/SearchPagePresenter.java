package com.example.movieapp.mvp.presenter.search_page;

import com.example.movieapp.mvp.presenter.search_page.button.ISearchPageButtonPresenter;
import com.example.movieapp.mvp.view.search_page.ISearchPageView;
import com.example.movieapp.navigation.Screens;
import com.example.movieapp.utils.log.Logger;
import com.github.terrakok.cicerone.Router;

import moxy.MvpPresenter;

public class SearchPagePresenter extends MvpPresenter<ISearchPageView> {

    private final Router router;

    public SearchPagePresenter(Router router) {
        this.router = router;
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

    private final SearchPageButtonPresenter searchButtonPresenter = new SearchPageButtonPresenter();

    public ISearchPageButtonPresenter getPresenter() {
        return searchButtonPresenter;
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
