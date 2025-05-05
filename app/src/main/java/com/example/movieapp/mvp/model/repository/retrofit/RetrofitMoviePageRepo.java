package com.example.movieapp.mvp.model.repository.retrofit;

import com.example.movieapp.mvp.model.api.IDataSource;
import com.example.movieapp.mvp.model.api.dto.Movie;
import com.example.movieapp.mvp.model.repository.IMoviePageRepo;
import com.example.movieapp.utils.log.Logger;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RetrofitMoviePageRepo implements IMoviePageRepo {

    private final IDataSource api;

    public RetrofitMoviePageRepo(IDataSource api) {
        this.api = api;
    }

    @Override
    public Single<Movie> getMovie(String id) {
        Logger.logV(null);
        return api.getMovie(id).subscribeOn(Schedulers.io());
    }
}
