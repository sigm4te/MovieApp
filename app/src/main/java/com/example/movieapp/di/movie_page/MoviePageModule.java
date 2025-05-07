package com.example.movieapp.di.movie_page;

import com.example.movieapp.mvp.model.api.IDataSource;
import com.example.movieapp.mvp.model.repository.IMoviePageRepo;
import com.example.movieapp.mvp.model.repository.retrofit.RetrofitMoviePageRepo;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviePageModule {

    @Provides
    IMoviePageRepo moviePageRepo(IDataSource api) {
        return new RetrofitMoviePageRepo(api);
    }
}
