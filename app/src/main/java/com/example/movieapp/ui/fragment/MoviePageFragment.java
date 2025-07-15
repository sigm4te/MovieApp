package com.example.movieapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.R;
import com.example.movieapp.app.MovieApp;
import com.example.movieapp.mvp.presenter.movie_page.MoviePagePresenter;
import com.example.movieapp.mvp.view.movie_page.IMoviePageView;
import com.example.movieapp.mvp.view.movie_page.MovieViewModel;
import com.example.movieapp.navigation.Screens;
import com.example.movieapp.ui.BackButtonListener;
import com.example.movieapp.utils.image.GlideImageLoader;
import com.example.movieapp.utils.image.IImageLoader;
import com.example.movieapp.utils.log.Logger;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class MoviePageFragment extends MvpAppCompatFragment implements IMoviePageView, BackButtonListener {

    private static final IImageLoader<ImageView> imageLoader = new GlideImageLoader();

    private View view;
    private TextView titleTextView;
    private ImageView posterImageView;
    private TextView typeTextView;
    private TextView yearTextView;
    private TextView countryTextView;
    private TextView directorTextView;
    private TextView ratingTextView;
    private TextView plotTextView;

    @InjectPresenter
    MoviePagePresenter presenter;

    @ProvidePresenter
    MoviePagePresenter provideMoviePagePresenter() {
        Logger.logV(null);
        assert getArguments() != null;
        String title = getArguments().getString(Screens.MOVIE_PAGE_TITLE);
        String id = getArguments().getString(Screens.MOVIE_PAGE_ID);
        return new MoviePagePresenter(title, id);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.logV(null);
        view = inflater.inflate(R.layout.fragment_movie_page, container, false);
        return view;
    }

    @Override
    public void init() {
        Logger.logV(null);
        initViews();
        initListeners();
        Logger.logV("completed");
    }

    private void initViews() {
        titleTextView = view.findViewById(R.id.tv_movie_title);
        posterImageView = view.findViewById(R.id.iv_movie_poster);
        typeTextView = view.findViewById(R.id.tv_movie_type);
        yearTextView = view.findViewById(R.id.tv_movie_year);
        countryTextView = view.findViewById(R.id.tv_movie_country);
        directorTextView = view.findViewById(R.id.tv_movie_director);
        ratingTextView = view.findViewById(R.id.tv_movie_rating);
        plotTextView = view.findViewById(R.id.tv_movie_plot);
    }

    private void initListeners() {
        posterImageView.setOnClickListener((view) -> {
            presenter.onImageClick();
        });
    }

    @Override
    public void setData(String title, MovieViewModel movie) {
        Logger.logD(String.format("name = %s", title));
        updateToolbar(title);
        if (movie.id == null) {
            showToast(movie.name);
        } else {
            titleTextView.setText(movie.name);
            imageLoader.loadImage(movie.imageUrl, posterImageView);
            typeTextView.setText(movie.type);
            yearTextView.setText(movie.year);
            countryTextView.setText(movie.country);
            directorTextView.setText(movie.director);
            ratingTextView.setText(movie.rating);
            plotTextView.setText(movie.plot);
        }
    }

    private void updateToolbar(String title) {
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(title);
        actionBar.setSubtitle(R.string.toolbar_subtitle_movie);
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void release() {
        MovieApp.instance.releaseMovieSubcomponent();
    }

    @Override
    public boolean backPressed() {
        return presenter.backPressed();
    }
}
