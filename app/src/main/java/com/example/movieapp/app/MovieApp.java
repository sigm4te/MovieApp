package com.example.movieapp.app;

import android.app.Application;

import com.example.movieapp.di.AppComponent;
import com.example.movieapp.di.DaggerAppComponent;
import com.example.movieapp.di.base.AppModule;
import com.example.movieapp.di.movie.MovieSubcomponent;
import com.example.movieapp.di.search.SearchSubcomponent;
import com.example.movieapp.utils.log.Logger;
import com.google.android.material.color.DynamicColors;

public class MovieApp extends Application {

    public static MovieApp instance;

    private AppComponent appComponent;
    private SearchSubcomponent searchSubcomponent;
    private MovieSubcomponent movieSubcomponent;

    @Override
    public void onCreate() {
        Logger.logV(null);
        super.onCreate();
        DynamicColors.applyToActivitiesIfAvailable(this);
        instance = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    private void initSearchSubcomponent() {
        AppComponent appComponent = this.appComponent;
        if (appComponent == null) {
            throw new IllegalStateException("AppComponent must be initialized");
        }
        this.searchSubcomponent = appComponent.searchSubcomponent();
    }

    private void initMovieSubcomponent() {
        SearchSubcomponent searchSubcomponent = this.searchSubcomponent;
        if (searchSubcomponent == null) {
            initSearchSubcomponent();
        }
        assert searchSubcomponent != null;
        this.movieSubcomponent = searchSubcomponent.movieSubcomponent();
    }

    public void releaseSearchSubcomponent() {
        searchSubcomponent = null;
    }

    public void releaseMovieSubcomponent() {
        movieSubcomponent = null;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public SearchSubcomponent getSearchSubcomponent() {
        if (searchSubcomponent == null) {
            initSearchSubcomponent();
        }
        return searchSubcomponent;
    }

    public MovieSubcomponent getMovieSubcomponent() {
        if (movieSubcomponent == null) {
            initMovieSubcomponent();
        }
        return movieSubcomponent;
    }
}
