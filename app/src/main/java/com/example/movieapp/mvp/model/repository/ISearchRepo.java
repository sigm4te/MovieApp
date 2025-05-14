package com.example.movieapp.mvp.model.repository;

import com.example.movieapp.mvp.model.api.dto.Search;

import io.reactivex.rxjava3.core.Single;

public interface ISearchRepo {
    Single<Search> getSearch(String query);
}
