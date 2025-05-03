package com.example.movieapp.utils.api;

import androidx.annotation.NonNull;

import com.example.movieapp.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Перехватчик запросов для добавления к каждому запросу ключа API
 */
public class ApiKeyInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalUrl = original.url();

        HttpUrl newUrl = originalUrl.newBuilder()
                .addQueryParameter("apikey", BuildConfig.API_KEY)
                .build();

        Request newRequest = original.newBuilder()
                .url(newUrl)
                .build();

        return chain.proceed(newRequest);
    }
}
