package com.example.movieapp.mvp.model.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.movieapp.mvp.model.database.entity.MovieEntity;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MovieEntity movie);

    @Insert
    void insert(List<MovieEntity> movieList);

    @Update
    void update(MovieEntity movie);

    @Update
    void update(List<MovieEntity> movieList);

    @Delete
    void delete(MovieEntity movie);

    @Delete
    void delete(List<MovieEntity> movieList);

    @Query("SELECT * FROM movies")
    List<MovieEntity> getAll();

    @Query("SELECT * FROM movies WHERE id = :id LIMIT 1")
    MovieEntity findById(String id);
}
