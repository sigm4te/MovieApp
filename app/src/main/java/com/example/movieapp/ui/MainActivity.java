package com.example.movieapp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.movieapp.app.MovieApp;
import com.example.movieapp.R;
import com.example.movieapp.mvp.presenter.base.MainPresenter;
import com.example.movieapp.mvp.view.base.IMainView;
import com.example.movieapp.utils.log.Logger;
import com.github.terrakok.cicerone.Navigator;
import com.github.terrakok.cicerone.NavigatorHolder;
import com.github.terrakok.cicerone.Router;
import com.github.terrakok.cicerone.androidx.AppNavigator;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class MainActivity extends MvpAppCompatActivity implements IMainView {

    private NavigatorHolder navigatorHolder;
    private Navigator navigator;

    @InjectPresenter
    MainPresenter presenter;

    @ProvidePresenter
    MainPresenter provideMainPresenter() {
        Logger.logV(null);
        Router router = MovieApp.instance.getRouter();
        return new MainPresenter(router);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.logV(null);
        super.onCreate(savedInstanceState);
        initToolbar();
        initNavigator();
        setContentView(R.layout.activity_main);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initNavigator() {
        navigatorHolder = MovieApp.instance.getNavigatorHolder();
        navigator = new AppNavigator(this, R.id.container, getSupportFragmentManager());
    }

    @Override
    protected void onResumeFragments() {
        Logger.logV(null);
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        Logger.logV(null);
        super.onPause();
        navigatorHolder.removeNavigator();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment instanceof BackButtonListener && ((BackButtonListener) fragment).backPressed()) {
                return;
            }
        }
        presenter.backClicked();
    }
}
