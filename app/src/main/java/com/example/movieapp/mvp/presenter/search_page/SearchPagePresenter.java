package com.example.movieapp.mvp.presenter.search_page;

import android.annotation.SuppressLint;

import com.example.movieapp.app.MovieApp;
import com.example.movieapp.mvp.model.cache.IHistoryCache;
import com.example.movieapp.mvp.presenter.search_page.button.ISearchPageButtonPresenter;
import com.example.movieapp.mvp.presenter.search_page.list.ISearchHistoryListPresenter;
import com.example.movieapp.mvp.presenter.search_page.text.ISearchPageEditTextPresenter;
import com.example.movieapp.mvp.view.search_page.ISearchPageView;
import com.example.movieapp.mvp.view.search_page.list.ISearchHistoryItemView;
import com.example.movieapp.navigation.Screens;
import com.example.movieapp.utils.log.Logger;
import com.github.terrakok.cicerone.Router;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;

public class SearchPagePresenter extends MvpPresenter<ISearchPageView> {

    @Inject
    Scheduler scheduler;
    @Inject
    Router router;
    @Inject
    IHistoryCache historyCache;

    private final SearchPageButtonPresenter searchButtonPresenter = new SearchPageButtonPresenter();
    private final SearchPageEditTextPresenter searchEditTextPresenter = new SearchPageEditTextPresenter();
    private final SearchHistoryListPresenter searchHistoryListPresenter = new SearchHistoryListPresenter();

    public SearchPagePresenter() {
        MovieApp.instance.getSearchSubcomponent().inject(this);
    }

    private class SearchPageButtonPresenter implements ISearchPageButtonPresenter {
        @Override
        public void onClick(String query) {
            if (!query.isBlank()) {
                router.navigateTo(new Screens.SearchResultScreen(query));
            }
        }
    }

    private class SearchPageEditTextPresenter implements ISearchPageEditTextPresenter {
        @Override
        public void onTextChange(String chars) {
            setHistoryData(chars);
        }
    }

    private class SearchHistoryListPresenter implements ISearchHistoryListPresenter {
        private final List<String> searchHistoryList = new ArrayList<>();

        @Override
        public void onItemClick(ISearchHistoryItemView view) {
            int index = view.getPos();
            Logger.logD(String.format("index = %s", index));
            if (!searchHistoryList.isEmpty()) {
                String query = searchHistoryList.get(index);
                router.navigateTo(new Screens.SearchResultScreen(query));
            }
        }

        @Override
        public void bindView(ISearchHistoryItemView view) {
            int index = view.getPos();
            String searchHistoryItem = searchHistoryList.get(index);
            view.setData(searchHistoryItem);
        }

        @Override
        public int getCount() {
            return searchHistoryList.size();
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

    @SuppressLint("CheckResult")
    private void setHistoryData(String chars) {
        Logger.logD(String.format("chars = %s", chars));
        searchHistoryListPresenter.searchHistoryList.clear();
        if (!chars.isEmpty()) {
            historyCache.getSearch(chars).observeOn(scheduler).subscribe(
                    (search) -> {
                        searchHistoryListPresenter.searchHistoryList.clear();
                        if (!search.isEmpty()) {
                            searchHistoryListPresenter.searchHistoryList.addAll(search);
                        }
                        getViewState().updateData();
                    },
                    (e) -> {
                        Logger.logE(String.format("error = %s", e.getMessage()));
                    }
            );
        }
        getViewState().updateData();
    }

    public ISearchPageButtonPresenter getSearchButtonPresenter() {
        return searchButtonPresenter;
    }

    public ISearchPageEditTextPresenter getEditTextPresenter() {
        return searchEditTextPresenter;
    }

    public ISearchHistoryListPresenter getHistoryListPresenter() {
        return searchHistoryListPresenter;
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
