package com.example.movieapp.mvp.model.repository.retrofit;

import com.example.movieapp.mvp.model.api.IDataSource;
import com.example.movieapp.mvp.model.api.dto.Movie;
import com.example.movieapp.mvp.model.cache.IMovieCache;
import com.example.movieapp.mvp.model.network.INetworkStatus;
import com.example.movieapp.mvp.model.repository.IMovieRepo;
import com.example.movieapp.utils.log.Logger;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RetrofitMovieRepo implements IMovieRepo {

    private final IDataSource api;
    private final INetworkStatus networkStatus;
    private final IMovieCache cache;

    public RetrofitMovieRepo(IDataSource api, INetworkStatus networkStatus, IMovieCache cache) {
        this.api = api;
        this.networkStatus = networkStatus;
        this.cache = cache;
    }

    @Override
    public Single<Movie> getMovie(String id) {
        Logger.logV(null);
        return networkStatus.isOnlineSingle().flatMap((isOnline) -> {
            if (isOnline) {
                return api.getMovie(id).flatMap((movie) -> cache.putMovie(movie).toSingleDefault(movie));
            } else {
                return cache.getMovie(id);
            }
        }).subscribeOn(Schedulers.io());
    }
}
