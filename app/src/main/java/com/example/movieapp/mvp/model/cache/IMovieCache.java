package com.example.movieapp.mvp.model.cache;

import com.example.movieapp.mvp.model.api.dto.Movie;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface IMovieCache {
    Single<Movie> getMovie(String id);
    Completable putMovie(Movie movie);
}
