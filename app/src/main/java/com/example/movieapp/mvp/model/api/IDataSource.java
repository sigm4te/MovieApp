package com.example.movieapp.mvp.model.api;

import com.example.movieapp.mvp.model.api.dto.Search;
import com.example.movieapp.mvp.model.api.dto.Movie;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IDataSource {

    @GET("/")
    Single<Search> getSearchResult(@Query("s") String query);

    @GET("/")
    Single<Movie> getMovie(@Query("i") String id);
}
