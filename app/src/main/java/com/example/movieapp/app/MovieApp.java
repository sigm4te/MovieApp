package com.example.movieapp.app;

import android.app.Application;

import com.example.movieapp.utils.log.Logger;
import com.github.terrakok.cicerone.Cicerone;
import com.github.terrakok.cicerone.NavigatorHolder;
import com.github.terrakok.cicerone.Router;
import com.google.android.material.color.DynamicColors;

public class MovieApp extends Application {

    public static MovieApp instance;

    private Cicerone<Router> cicerone;

    @Override
    public void onCreate() {
        Logger.logV(null);
        super.onCreate();
        DynamicColors.applyToActivitiesIfAvailable(this);
        instance = this;
        cicerone = Cicerone.create();
    }

    public NavigatorHolder getNavigatorHolder() {
        Logger.logV(null);
        return cicerone.getNavigatorHolder();
    }

    public Router getRouter() {
        Logger.logV(null);
        return cicerone.getRouter();
    }
}
