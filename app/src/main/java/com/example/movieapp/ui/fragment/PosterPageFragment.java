package com.example.movieapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.R;
import com.example.movieapp.app.MovieApp;
import com.example.movieapp.mvp.presenter.poster_page.PosterPagePresenter;
import com.example.movieapp.mvp.view.poster_page.IPosterPageView;
import com.example.movieapp.navigation.Screens;
import com.example.movieapp.ui.BackButtonListener;
import com.example.movieapp.utils.image.GlideImageLoader;
import com.example.movieapp.utils.image.IImageLoader;
import com.example.movieapp.utils.log.Logger;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class PosterPageFragment extends MvpAppCompatFragment implements IPosterPageView, BackButtonListener {

    private static final IImageLoader<ImageView> imageLoader = new GlideImageLoader();

    private View view;
    private ImageView posterImageView;

    @InjectPresenter
    PosterPagePresenter presenter;

    @ProvidePresenter
    PosterPagePresenter providePosterPagePresenter() {
        Logger.logV(null);
        assert getArguments() != null;
        String title = getArguments().getString(Screens.POSTER_PAGE_TITLE);
        String imageUrl = getArguments().getString(Screens.POSTER_PAGE_URL);
        return new PosterPagePresenter(title, imageUrl);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_poster_page, container, false);
        Logger.logV(null);
        return view;
    }

    @Override
    public void init() {
        Logger.logV(null);
        initViews();
        Logger.logV("completed");
    }

    private void initViews() {
        posterImageView = view.findViewById(R.id.iv_poster);
    }

    @Override
    public void setData(String title, String imageUrl) {
        Logger.logV(null);
        updateToolbar(title);
        imageLoader.loadImage(imageUrl, posterImageView);
    }

    private void updateToolbar(String title) {
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(title);
        actionBar.setSubtitle(getString(R.string.toolbar_subtitle_poster));
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
