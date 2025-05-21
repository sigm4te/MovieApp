package com.example.movieapp.mvp.model.cache;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface IHistoryCache {
    Single<List<String>> getSearch(String chars);
}
