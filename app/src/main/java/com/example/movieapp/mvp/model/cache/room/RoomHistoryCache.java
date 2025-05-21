package com.example.movieapp.mvp.model.cache.room;

import com.example.movieapp.mvp.model.cache.IHistoryCache;
import com.example.movieapp.mvp.model.database.AppDatabase;
import com.example.movieapp.mvp.model.database.entity.SearchHistoryEntity;
import com.example.movieapp.utils.log.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RoomHistoryCache implements IHistoryCache {

    private final AppDatabase db;

    public RoomHistoryCache(AppDatabase db) {
        this.db = db;
    }

    @Override
    public Single<List<String>> getSearch(String chars) {
        return Single.fromCallable(() -> {
            List<SearchHistoryEntity> searchHistoryList = db.searchHistoryDao().findByChars(chars + "%");
            List<String> searchQueryList = new ArrayList<>();

            for (SearchHistoryEntity searchHistoryEntity : searchHistoryList) {
                String searchQuery = searchHistoryEntity.query;
                searchQueryList.add(searchQuery);
            }
            Logger.logD(String.format("list = %s", searchQueryList));
            return searchQueryList;
        }).subscribeOn(Schedulers.io());
    }
}
