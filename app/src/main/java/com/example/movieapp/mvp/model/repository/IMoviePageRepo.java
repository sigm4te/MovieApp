package com.example.movieapp.mvp.model.repository;

import com.example.movieapp.mvp.model.api.dto.Movie;

import io.reactivex.rxjava3.core.Single;

public interface IMoviePageRepo {
    Single<Movie> getMovie(String id);
}
