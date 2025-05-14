package com.example.movieapp.mvp.model.cache.room;

import com.example.movieapp.mvp.model.api.dto.SearchResultItem;
import com.example.movieapp.mvp.model.cache.ISearchCache;
import com.example.movieapp.mvp.model.database.AppDatabase;
import com.example.movieapp.mvp.model.database.entity.SearchHistoryEntity;
import com.example.movieapp.mvp.model.database.entity.SearchResultEntity;
import com.example.movieapp.mvp.model.api.dto.Search;
import com.example.movieapp.utils.log.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RoomSearchCache implements ISearchCache {

    private final AppDatabase db;

    public RoomSearchCache(AppDatabase db) {
        this.db = db;
    }

    @Override
    public Single<Search> getSearch(String query) {
        Logger.logV(null);
        return Single.fromCallable(() -> {
            SearchHistoryEntity searchHistory = db.searchHistoryDao().findByQuery(query);
            List<String> ids = searchHistory.getIds();

            List<SearchResultItem> searchResult = new ArrayList<>();
            for (String id : ids) {
                SearchResultItem searchResultItem = getSearchResultItem(id);
                searchResult.add(searchResultItem);
            }
            return new Search(searchResult);
        });
    }

    @Override
    public Completable putSearch(String query, Search search) {
        Logger.logV(null);
        return Completable.fromAction(() -> {
            List<String> ids = new ArrayList<>();
            List<SearchResultItem> searchResult = search.getItems();

            for (SearchResultItem searchResultItem : searchResult) {
                String id = searchResultItem.getId();
                ids.add(id);
                putSearchResultItem(searchResultItem);
            }
            db.searchHistoryDao().insert(new SearchHistoryEntity(query, ids));
        }).subscribeOn(Schedulers.io());
    }

    private SearchResultItem getSearchResultItem(String id) {
        SearchResultEntity searchResultEntity = db.searchResultDao().findById(id);
        return new SearchResultItem(
                searchResultEntity.getId(),
                searchResultEntity.getTitle(),
                searchResultEntity.getType(),
                searchResultEntity.getYear(),
                searchResultEntity.getImageUrl()
        );
    }

    private void putSearchResultItem(SearchResultItem searchResultItem) {
        db.searchResultDao().insert(new SearchResultEntity(
                searchResultItem.getId(),
                searchResultItem.getTitle(),
                searchResultItem.getType(),
                searchResultItem.getYear(),
                searchResultItem.getImageUrl()
        ));
    }
}
