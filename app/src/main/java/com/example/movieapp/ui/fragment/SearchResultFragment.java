package com.example.movieapp.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.app.MovieApp;
import com.example.movieapp.R;
import com.example.movieapp.mvp.model.repository.ISearchResultRepo;
import com.example.movieapp.mvp.model.repository.retrofit.RetrofitSearchResultRepo;
import com.example.movieapp.mvp.presenter.search_result.SearchResultPresenter;
import com.example.movieapp.mvp.view.search_result.ISearchResultView;
import com.example.movieapp.navigation.Screens;
import com.example.movieapp.ui.BackButtonListener;
import com.example.movieapp.ui.adapter.SearchResultAdapter;
import com.example.movieapp.utils.log.Logger;
import com.github.terrakok.cicerone.Router;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class SearchResultFragment extends MvpAppCompatFragment implements ISearchResultView, BackButtonListener {

    private View view;
    private RecyclerView recyclerView;
    private SearchResultAdapter adapter;

    @InjectPresenter
    SearchResultPresenter presenter;

    @ProvidePresenter
    SearchResultPresenter provideSearchResultPresenter() {
        Logger.logV(null);
        ISearchResultRepo searchResultRepo = new RetrofitSearchResultRepo(MovieApp.instance.getApi());
        Router router = MovieApp.instance.getRouter();
        assert getArguments() != null;
        String query = getArguments().getString(Screens.QUERY_STRING_ARG);
        return new SearchResultPresenter(AndroidSchedulers.mainThread(), router, searchResultRepo, query);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.logV(null);
        view = inflater.inflate(R.layout.fragment_search_result, container, false);
        recyclerView = view.findViewById(R.id.rv_search_result);
        return view;
    }

    @Override
    public void init() {
        Logger.logV(null);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new SearchResultAdapter(presenter.getPresenter());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        Logger.logV("completed");
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void updateData() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean backPressed() {
        return presenter.backPressed();
    }
}
