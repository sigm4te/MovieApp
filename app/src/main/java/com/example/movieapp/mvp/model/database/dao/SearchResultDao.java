package com.example.movieapp.mvp.model.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.movieapp.mvp.model.database.entity.SearchResultEntity;

import java.util.List;

@Dao
public interface SearchResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SearchResultEntity searchResultItem);

    @Insert
    void insert(List<SearchResultEntity> searchResult);

    @Update
    void update(SearchResultEntity searchResultItem);

    @Update
    void update(List<SearchResultEntity> searchResult);

    @Delete
    void delete(SearchResultEntity searchResultItem);

    @Delete
    void delete(List<SearchResultEntity> searchResult);

    @Query("SELECT * FROM search_result")
    List<SearchResultEntity> getAll();

    @Query("SELECT * FROM search_result WHERE id = :id")
    SearchResultEntity findById(String id);
}
