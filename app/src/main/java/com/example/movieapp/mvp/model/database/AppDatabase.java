package com.example.movieapp.mvp.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.movieapp.mvp.model.database.dao.MovieDao;
import com.example.movieapp.mvp.model.database.dao.SearchHistoryDao;
import com.example.movieapp.mvp.model.database.dao.SearchResultDao;
import com.example.movieapp.mvp.model.database.entity.MovieEntity;
import com.example.movieapp.mvp.model.database.entity.SearchHistoryEntity;
import com.example.movieapp.mvp.model.database.entity.SearchResultEntity;

@Database(entities = {SearchHistoryEntity.class, SearchResultEntity.class, MovieEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "database.db";

    public abstract SearchHistoryDao searchHistoryDao();
    public abstract SearchResultDao searchResultDao();
    public abstract MovieDao movieDao();
}
