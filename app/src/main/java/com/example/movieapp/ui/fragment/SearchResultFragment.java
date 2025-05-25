package com.example.movieapp.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.app.MovieApp;
import com.example.movieapp.mvp.presenter.search_result.SearchResultPresenter;
import com.example.movieapp.mvp.view.search_result.ISearchResultView;
import com.example.movieapp.navigation.Screens;
import com.example.movieapp.ui.BackButtonListener;
import com.example.movieapp.ui.adapter.SearchResultAdapter;
import com.example.movieapp.utils.log.Logger;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class SearchResultFragment extends MvpAppCompatFragment implements ISearchResultView, BackButtonListener {

    private View view;
    private RecyclerView resultRecyclerView;
    private SearchResultAdapter resultAdapter;

    @InjectPresenter
    SearchResultPresenter presenter;

    @ProvidePresenter
    SearchResultPresenter provideSearchResultPresenter() {
        Logger.logV(null);
        assert getArguments() != null;
        String query = getArguments().getString(Screens.SEARCH_RESULT_QUERY);
        return new SearchResultPresenter(query);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.logV(null);
        view = inflater.inflate(R.layout.fragment_search_result, container, false);
        resultRecyclerView = view.findViewById(R.id.rv_search_result);
        return view;
    }

    @Override
    public void init() {
        Logger.logV(null);
        initAdapter();
        Logger.logV("completed");
    }

    private void initAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        resultAdapter = new SearchResultAdapter(presenter.getPresenter());
        resultRecyclerView.setLayoutManager(layoutManager);
        resultRecyclerView.setAdapter(resultAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void setData(String query) {
        updateToolbar(query);
        resultAdapter.notifyDataSetChanged();
    }

    private void updateToolbar(String query) {
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(query);
        actionBar.setSubtitle(getString(R.string.toolbar_subtitle_search));
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
