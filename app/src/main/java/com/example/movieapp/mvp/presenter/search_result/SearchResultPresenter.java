package com.example.movieapp.mvp.presenter.search_result;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

import com.example.movieapp.app.MovieApp;
import com.example.movieapp.mvp.model.repository.ISearchResultRepo;
import com.example.movieapp.mvp.model.api.dto.SearchResultItem;
import com.example.movieapp.mvp.presenter.base.ViewModelMapper;
import com.example.movieapp.mvp.presenter.search_result.list.ISearchResultListPresenter;
import com.example.movieapp.mvp.view.search_result.ISearchResultView;
import com.example.movieapp.mvp.view.search_result.list.ISearchResultItemView;
import com.example.movieapp.navigation.Screens;
import com.example.movieapp.utils.log.Logger;
import com.github.terrakok.cicerone.Router;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import moxy.MvpPresenter;

public class SearchResultPresenter extends MvpPresenter<ISearchResultView> {

    private static final String NO_RESULT = "No Result";

    @Inject
    Scheduler scheduler;
    @Inject
    Router router;
    @Inject
    ISearchResultRepo searchResultRepo;

    private final String query;

    public SearchResultPresenter(String query) {
        this.query = query;
        MovieApp.instance.getSearchResultSubcomponent().inject(this);
    }

    private class SearchResultListPresenter implements ISearchResultListPresenter {
        private final List<SearchResultItem> searchResultList = new ArrayList<>();

        @Override
        public void onItemClick(ISearchResultItemView view) {
            int index = view.getPos();
            Logger.logD("index = " + index);
            String id = searchResultList.get(index).getId();
            if (id != null) {
                router.navigateTo(new Screens.MoviePageScreen(id));
            }
        }

        @Override
        public void bindView(ISearchResultItemView view) {
            int index = view.getPos();
            SearchResultItem searchResultItem = searchResultList.get(index);
            setRecyclerData(view, searchResultItem);
        }

        @Override
        public int getCount() {
            return searchResultList.size();
        }
    }

    @Override
    protected void onFirstViewAttach() {
        Logger.logV(null);
        super.onFirstViewAttach();
        getViewState().init();
        setData();
    }

    @Override
    public void onDestroy() {
        Logger.logV(null);
        super.onDestroy();
        getViewState().release();
    }

    private void setRecyclerData(ISearchResultItemView view, SearchResultItem searchResultItem) {
        Logger.logV(null);
        searchResultItem.getTitleObservable().subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Logger.logD(null);
            }

            @Override
            public void onNext(@NonNull String title) {
                Logger.logD(String.format("title = %s", title));
                view.setSearchResultItem(ViewModelMapper.mapSearchResultItem(searchResultItem));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Logger.logE(String.format("error = %s", e.getMessage()));
            }

            @Override
            public void onComplete() {
                Logger.logD(null);
            }
        });
    }

    @SuppressLint("CheckResult")
    @UiThread
    private void setData() {
        Logger.logV(null);
        searchResultRepo.getSearch(query).observeOn(scheduler).subscribe(
                (search) -> {
                    searchResultListPresenter.searchResultList.clear();
                    if (search.getItems() == null) {
                        searchResultListPresenter.searchResultList.add(new SearchResultItem(null, NO_RESULT, null, null, null));
                    } else {
                        searchResultListPresenter.searchResultList.addAll(search.getItems());
                    }
                    getViewState().updateData();
                },
                (e) -> {
                    Logger.logE(String.format("error = %s", e.getMessage()));
                }
        );
        getViewState().updateData();
    }

    private final SearchResultListPresenter searchResultListPresenter = new SearchResultListPresenter();

    public ISearchResultListPresenter getPresenter() {
        return searchResultListPresenter;
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
