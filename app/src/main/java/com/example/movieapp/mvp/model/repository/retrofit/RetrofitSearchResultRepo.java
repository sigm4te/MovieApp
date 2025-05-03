package com.example.movieapp.mvp.model.repository.retrofit;

import com.example.movieapp.mvp.model.api.IDataSource;
import com.example.movieapp.mvp.model.api.dto.Search;
import com.example.movieapp.mvp.model.repository.ISearchResultRepo;
import com.example.movieapp.utils.log.Logger;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RetrofitSearchResultRepo implements ISearchResultRepo {

    private final IDataSource api;

    public RetrofitSearchResultRepo(IDataSource api) {
        this.api = api;
    }

    @Override
    public Single<Search> getSearch(String query) {
        Logger.logV(null);
        return api.getSearchResult(query).subscribeOn(Schedulers.io());
    }
}
