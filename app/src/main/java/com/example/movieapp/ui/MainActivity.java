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
import com.github.terrakok.cicerone.androidx.AppNavigator;

import javax.inject.Inject;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements IMainView {

    @Inject
    NavigatorHolder navigatorHolder;
    Navigator navigator;

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.logV(null);
        super.onCreate(savedInstanceState);
        initToolbar();
        initNavigator();
        setContentView(R.layout.activity_main);
        MovieApp.instance.getAppComponent().inject(this);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
    }

    private void initNavigator() {
        navigator = new AppNavigator(this, R.id.container_main, getSupportFragmentManager());
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
