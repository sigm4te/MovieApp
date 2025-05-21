package com.example.movieapp.mvp.model.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.movieapp.mvp.model.database.entity.SearchHistoryEntity;

import java.util.List;

@Dao
public interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SearchHistoryEntity searchHistoryItem);

    @Insert
    void insert(List<SearchHistoryEntity> searchHistory);

    @Update
    void update(SearchHistoryEntity searchHistoryItem);

    @Update
    void update(List<SearchHistoryEntity> searchHistory);

    @Delete
    void delete(SearchHistoryEntity searchHistoryItem);

    @Delete
    void delete(List<SearchHistoryEntity> searchHistory);

    @Query("SELECT * FROM search_history")
    List<SearchHistoryEntity> getAll();

    @Query("SELECT * FROM search_history WHERE search_query = :query")
    SearchHistoryEntity findByQuery(String query);

    @Query("SELECT * FROM search_history WHERE search_query LIKE :chars ORDER BY search_query")
    List<SearchHistoryEntity> findByChars(String chars);
}
