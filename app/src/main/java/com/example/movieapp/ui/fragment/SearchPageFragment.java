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
import com.example.movieapp.mvp.presenter.search_page.SearchPagePresenter;
import com.example.movieapp.mvp.view.search_page.ISearchPageView;
import com.example.movieapp.ui.BackButtonListener;
import com.example.movieapp.utils.log.Logger;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class SearchPageFragment extends MvpAppCompatFragment implements ISearchPageView, BackButtonListener {

    private View view;
    private SearchView searchView;
    private Button button;

    @InjectPresenter
    SearchPagePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.logV(null);
        view = inflater.inflate(R.layout.fragment_search_page, container, false);
        return view;
    }

    @Override
    public void init() {
        Logger.logV(null);
        searchView = view.findViewById(R.id.sv_search);
        button = view.findViewById(R.id.btn_search);
        initListeners();
        Logger.logV("completed");
    }

    private void initListeners() {
        button.setOnClickListener((view) -> {
            String query = searchView.getQuery().toString();
            startSearch(query);
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                startSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void startSearch(String query) {
        presenter.getPresenter().onClick(query);
    }

    @Override
    public void release() {
        MovieApp.instance.releaseSearchSubcomponent();
    }

    @Override
    public boolean backPressed() {
        return presenter.backPressed();
    }
}
