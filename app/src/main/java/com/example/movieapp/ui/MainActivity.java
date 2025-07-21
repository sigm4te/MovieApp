package com.example.movieapp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.movieapp.R;
import com.example.movieapp.app.MovieApp;
import com.example.movieapp.mvp.presenter.base.MainPresenter;
import com.example.movieapp.mvp.view.base.IMainView;
import com.example.movieapp.utils.log.Logger;
import com.github.terrakok.cicerone.Navigator;
import com.github.terrakok.cicerone.NavigatorHolder;
import com.github.terrakok.cicerone.androidx.AppNavigator;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
        setContentView(R.layout.activity_main);
        initToolbar();
        initBottomNavigation();
        initNavigator();
        MovieApp.instance.getAppComponent().inject(this);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.tb_main);
        setSupportActionBar(toolbar);
    }

    private void initBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bnv_main);
        bottomNavigationView.setOnItemSelectedListener((item) -> {
            if (item.getItemId() == R.id.bnv_main_item_search && !item.isChecked()) {
                Logger.logD(String.format("menu item = %s", item.getTitle()));
                // action
                item.setChecked(true);
                return true;
            } else if (item.getItemId() == R.id.bnv_main_item_favorites && !item.isChecked()) {
                Logger.logD(String.format("menu item = %s", item.getTitle()));
                // action
                item.setChecked(true);
                return true;
            } else if (item.getItemId() == R.id.bnv_main_item_settings && !item.isChecked()) {
                Logger.logD(String.format("menu item = %s", item.getTitle()));
                // action
                item.setChecked(true);
                return true;
            }
            return false;
        });
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
