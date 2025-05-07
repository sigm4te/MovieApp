package com.example.movieapp.app;

import android.app.Application;

import com.example.movieapp.di.AppComponent;
import com.example.movieapp.di.DaggerAppComponent;
import com.example.movieapp.di.base.AppModule;
import com.example.movieapp.di.movie_page.MoviePageSubcomponent;
import com.example.movieapp.di.search_page.SearchPageSubcomponent;
import com.example.movieapp.di.search_result.SearchResultSubcomponent;
import com.example.movieapp.utils.log.Logger;
import com.google.android.material.color.DynamicColors;

public class MovieApp extends Application {

    public static MovieApp instance;

    private AppComponent appComponent;
    private SearchPageSubcomponent searchPageSubcomponent;
    private SearchResultSubcomponent searchResultSubcomponent;
    private MoviePageSubcomponent moviePageSubcomponent;

    @Override
    public void onCreate() {
        Logger.logV(null);
        super.onCreate();
        DynamicColors.applyToActivitiesIfAvailable(this);
        instance = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    private void initSearchPageSubcomponent() {
        AppComponent appComponent = this.appComponent;
        if (appComponent == null) {
            throw new IllegalStateException("AppComponent must be initialized");
        }
        this.searchPageSubcomponent = appComponent.searchPageSubcomponent();
    }

    private void initSearchResultSubcomponent() {
        SearchPageSubcomponent searchPageSubcomponent = this.searchPageSubcomponent;
        if (searchPageSubcomponent == null) {
            initSearchPageSubcomponent();
        }
        assert searchPageSubcomponent != null;
        this.searchResultSubcomponent = searchPageSubcomponent.searchResultSubcomponent();
    }

    private void initMoviePageSubcomponent() {
        SearchResultSubcomponent searchResultSubcomponent = this.searchResultSubcomponent;
        if (searchResultSubcomponent == null) {
            initSearchResultSubcomponent();
        }
        assert searchResultSubcomponent != null;
        this.moviePageSubcomponent = searchResultSubcomponent.moviePageSubcomponent();
    }

    public void releaseSearchPageSubcomponent() {
        searchPageSubcomponent = null;
    }

    public void releaseSearchResultSubcomponent() {
        searchResultSubcomponent = null;
    }

    public void releaseMoviePageSubcomponent() {
        moviePageSubcomponent = null;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public SearchPageSubcomponent getSearchPageSubcomponent() {
        if (searchPageSubcomponent == null) {
            initSearchPageSubcomponent();
        }
        return searchPageSubcomponent;
    }

    public SearchResultSubcomponent getSearchResultSubcomponent() {
        if (searchResultSubcomponent == null) {
            initSearchResultSubcomponent();
        }
        return searchResultSubcomponent;
    }

    public MoviePageSubcomponent getMoviePageSubcomponent() {
        if (moviePageSubcomponent == null) {
            initMoviePageSubcomponent();
        }
        return moviePageSubcomponent;
    }
}
