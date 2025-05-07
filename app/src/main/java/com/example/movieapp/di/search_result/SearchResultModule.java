package com.example.movieapp.di.search_result;

import com.example.movieapp.mvp.model.api.IDataSource;
import com.example.movieapp.mvp.model.repository.ISearchResultRepo;
import com.example.movieapp.mvp.model.repository.retrofit.RetrofitSearchResultRepo;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchResultModule {

    @Provides
    ISearchResultRepo searchResultRepo(IDataSource api) {
        return new RetrofitSearchResultRepo(api);
    }
}
