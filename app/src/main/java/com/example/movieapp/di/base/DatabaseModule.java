package com.example.movieapp.di.base;

import androidx.room.Room;

import com.example.movieapp.app.MovieApp;
import com.example.movieapp.mvp.model.database.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    AppDatabase database() {
        return Room
                .databaseBuilder(MovieApp.instance, AppDatabase.class, AppDatabase.DB_NAME)
                .build();
    }
}
