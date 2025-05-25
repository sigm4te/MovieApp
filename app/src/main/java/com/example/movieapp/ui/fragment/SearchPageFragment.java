package com.example.movieapp.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.app.MovieApp;
import com.example.movieapp.mvp.presenter.search_page.SearchPagePresenter;
import com.example.movieapp.mvp.view.search_page.ISearchPageView;
import com.example.movieapp.ui.BackButtonListener;
import com.example.movieapp.ui.adapter.SearchHistoryAdapter;
import com.example.movieapp.utils.log.Logger;
import com.google.android.material.button.MaterialButton;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public class SearchPageFragment extends MvpAppCompatFragment implements ISearchPageView, BackButtonListener {

    private View view;
    private SearchView searchView;
    private MaterialButton searchButton;
    private RecyclerView historyRecyclerView;
    private SearchHistoryAdapter historyAdapter;

    @InjectPresenter
    SearchPagePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.logV(null);
        view = inflater.inflate(R.layout.fragment_search_page, container, false);
        historyRecyclerView = view.findViewById(R.id.rv_search_history);
        return view;
    }

    @Override
    public void init() {
        Logger.logV(null);
        initViews();
        initAdapter();
        initListeners();
        Logger.logV("completed");
    }

    private void initViews() {
        searchView = view.findViewById(R.id.sv_search);
        searchButton = view.findViewById(R.id.btn_search);
    }

    private void initAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        historyAdapter = new SearchHistoryAdapter(presenter.getHistoryListPresenter());
        historyRecyclerView.setLayoutManager(layoutManager);
        historyRecyclerView.setAdapter(historyAdapter);
    }

    private void initListeners() {
        searchButton.setOnClickListener((view) -> {
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
            public boolean onQueryTextChange(String query) {
                loadHistory(query);
                return false;
            }
        });
    }

    @Override
    public void setData() {
        updateToolbar();
    }

    private void updateToolbar() {
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(getString(R.string.toolbar_title_main));
        actionBar.setSubtitle(getString(R.string.toolbar_subtitle_main));
    }

    private void startSearch(String query) {
        presenter.getSearchButtonPresenter().onClick(query);
    }

    private void loadHistory(String query) {
        presenter.getEditTextPresenter().onTextChange(query);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void setHistoryData() {
        historyAdapter.notifyDataSetChanged();
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
