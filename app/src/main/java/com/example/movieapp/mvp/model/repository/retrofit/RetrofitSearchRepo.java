package com.example.movieapp.mvp.model.repository.retrofit;

import com.example.movieapp.mvp.model.api.IDataSource;
import com.example.movieapp.mvp.model.api.dto.Search;
import com.example.movieapp.mvp.model.cache.ISearchCache;
import com.example.movieapp.mvp.model.network.INetworkStatus;
import com.example.movieapp.mvp.model.repository.ISearchRepo;
import com.example.movieapp.utils.log.Logger;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RetrofitSearchRepo implements ISearchRepo {

    private final IDataSource api;
    private final INetworkStatus networkStatus;
    private final ISearchCache cache;

    public RetrofitSearchRepo(IDataSource api, INetworkStatus networkStatus, ISearchCache cache) {
        this.api = api;
        this.networkStatus = networkStatus;
        this.cache = cache;
    }

    @Override
    public Single<Search> getSearch(String query) {
        Logger.logV(null);
        return networkStatus.isOnlineSingle().flatMap((isOnline) -> {
            if (isOnline) {
                return api.getSearchResult(query).flatMap((search) -> cache.putSearch(query, search).toSingleDefault(search));
            } else {
                return cache.getSearch(query);
            }
        }).subscribeOn(Schedulers.io());
    }
}
