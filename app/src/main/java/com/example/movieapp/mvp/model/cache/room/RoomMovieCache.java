package com.example.movieapp.mvp.model.cache.room;

import com.example.movieapp.mvp.model.api.dto.Movie;
import com.example.movieapp.mvp.model.cache.IMovieCache;
import com.example.movieapp.mvp.model.database.AppDatabase;
import com.example.movieapp.mvp.model.database.entity.MovieEntity;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RoomMovieCache implements IMovieCache {

    private final AppDatabase db;

    public RoomMovieCache(AppDatabase db) {
        this.db = db;
    }

    @Override
    public Single<Movie> getMovie(String id) {
        return Single.fromCallable(() -> {
            MovieEntity movieEntity = db.movieDao().findById(id);
            return new Movie(
                    movieEntity.getId(),
                    movieEntity.getTitle(),
                    movieEntity.getImageUrl(),
                    movieEntity.getType(),
                    movieEntity.getYear(),
                    movieEntity.getCountry(),
                    movieEntity.getDirector(),
                    movieEntity.getRating(),
                    movieEntity.getPlot()
            );
        });
    }

    @Override
    public Completable putMovie(Movie movie) {
        return Completable.fromAction(() -> {
            db.movieDao().insert(new MovieEntity(
                    movie.getId(),
                    movie.getTitle(),
                    movie.getImageUrl(),
                    movie.getType(),
                    movie.getYear(),
                    movie.getCountry(),
                    movie.getDirector(),
                    movie.getRating(),
                    movie.getPlot()
            ));
        }).subscribeOn(Schedulers.io());
    }
}
