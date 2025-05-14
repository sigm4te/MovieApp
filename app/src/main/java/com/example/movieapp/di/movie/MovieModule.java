package com.example.movieapp.di.movie;

import com.example.movieapp.mvp.model.api.IDataSource;
import com.example.movieapp.mvp.model.cache.IMovieCache;
import com.example.movieapp.mvp.model.cache.room.RoomMovieCache;
import com.example.movieapp.mvp.model.database.AppDatabase;
import com.example.movieapp.mvp.model.network.INetworkStatus;
import com.example.movieapp.mvp.model.repository.IMovieRepo;
import com.example.movieapp.mvp.model.repository.retrofit.RetrofitMovieRepo;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieModule {

    @Provides
    IMovieRepo movieRepo(IDataSource api, INetworkStatus networkStatus, IMovieCache cache) {
        return new RetrofitMovieRepo(api, networkStatus, cache);
    }

    @Provides
    IMovieCache movieCache(AppDatabase db) {
        return new RoomMovieCache(db);
    }
}
