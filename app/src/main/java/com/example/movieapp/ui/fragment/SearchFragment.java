package com.example.movieapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.movieapp.app.MovieApp;
import com.example.movieapp.R;
import com.example.movieapp.mvp.presenter.search.SearchPresenter;
import com.example.movieapp.mvp.view.search.ISearchView;
import com.example.movieapp.ui.BackButtonListener;
import com.example.movieapp.utils.log.Logger;
import com.github.terrakok.cicerone.Router;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class SearchFragment extends MvpAppCompatFragment implements ISearchView, BackButtonListener {

    private View view;
    private SearchView searchView;
    private Button button;

    @InjectPresenter
    SearchPresenter presenter;

    @ProvidePresenter
    SearchPresenter provideSearchPresenter() {
        Logger.logV(null);
        Router router = MovieApp.instance.getRouter();
        return new SearchPresenter(router);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.logV(null);
        view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }

    @Override
    public void init() {
        Logger.logV(null);
        searchView = view.findViewById(R.id.sv_search);
        button = view.findViewById(R.id.button_search);
        initListeners();
        Logger.logV("completed");
    }

    private void initListeners() {
        button.setOnClickListener((view) -> {
            String query = searchView.getQuery().toString();
            presenter.getPresenter().onClick(query);
        });
    }

    @Override
    public boolean backPressed() {
        return presenter.backPressed();
    }
}
