package com.example.movieapp.di.base;

import com.example.movieapp.BuildConfig;
import com.example.movieapp.mvp.model.api.IDataSource;
import com.example.movieapp.mvp.model.network.INetworkStatus;
import com.example.movieapp.utils.network.AndroidNetworkStatus;
import com.example.movieapp.utils.api.ApiKeyInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Named("baseUrl")
    @Provides
    String baseUrl() {
        return BuildConfig.API_URL;
    }

    @Singleton
    @Provides
    Gson gson() {
        return new GsonBuilder()
                .setFieldNamingStrategy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Singleton
    @Provides
    OkHttpClient clientWithApiKeyInterceptor() {
        return new OkHttpClient.Builder()
                .addInterceptor(new ApiKeyInterceptor())
                .build();
    }

    @Singleton
    @Provides
    IDataSource api(@Named("baseUrl") String baseUrl, OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(IDataSource.class);
    }

    @Singleton
    @Provides
    INetworkStatus networkStatus() {
        return new AndroidNetworkStatus();
    }
}
