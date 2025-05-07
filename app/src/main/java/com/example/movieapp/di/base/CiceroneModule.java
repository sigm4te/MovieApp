package com.example.movieapp.di.base;

import com.github.terrakok.cicerone.Cicerone;
import com.github.terrakok.cicerone.NavigatorHolder;
import com.github.terrakok.cicerone.Router;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CiceroneModule {

    private final Cicerone<Router> cicerone = Cicerone.create();

    @Provides
    Cicerone<Router> cicerone() {
        return cicerone;
    }

    @Singleton
    @Provides
    NavigatorHolder navigatorHolder() {
        return cicerone.getNavigatorHolder();
    }

    @Singleton
    @Provides
    Router router() {
        return cicerone.getRouter();
    }
}
