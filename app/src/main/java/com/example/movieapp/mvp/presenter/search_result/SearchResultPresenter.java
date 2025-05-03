package com.example.movieapp.mvp.presenter.search_result;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

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

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import moxy.MvpPresenter;

public class SearchResultPresenter extends MvpPresenter<ISearchResultView> {

    private final Scheduler scheduler;
    private final Router router;
    private final ISearchResultRepo searchResultRepo;
    private final String query;

    public SearchResultPresenter(Scheduler scheduler, Router router, ISearchResultRepo searchResultRepo, String query) {
        this.scheduler = scheduler;
        this.router = router;
        this.searchResultRepo = searchResultRepo;
        this.query = query;
    }

    private class SearchResultListPresenter implements ISearchResultListPresenter {
        private final List<SearchResultItem> searchResultList = new ArrayList<>();

        @Override
        public void onItemClick(ISearchResultItemView view) {
            int index = view.getPos();
            Logger.logD("index = " + index);
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

    private void setRecyclerData(ISearchResultItemView view, SearchResultItem searchResultItem) {
        Logger.logV(null);
        searchResultItem.getTitleObservable().subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Logger.logD(null);
            }

            @Override
            public void onNext(@NonNull String name) {
                view.setSearchResultItem(ViewModelMapper.mapSearchResultItem(searchResultItem));
                Logger.logD(String.format("name = %s", name));
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
                    Logger.logD(String.format("search size = %s", search.getItems().size()));
                    searchResultListPresenter.searchResultList.clear();
                    if (search.getItems() == null) {
                        searchResultListPresenter.searchResultList.add(new SearchResultItem());
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
