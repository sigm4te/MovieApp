package com.example.movieapp.mvp.presenter.movie_page;

import android.annotation.SuppressLint;

import com.example.movieapp.app.MovieApp;
import com.example.movieapp.mvp.model.repository.IMovieRepo;
import com.example.movieapp.mvp.presenter.base.ViewModelMapper;
import com.example.movieapp.mvp.view.movie_page.IMoviePageView;
import com.example.movieapp.navigation.Screens;
import com.example.movieapp.utils.log.Logger;
import com.github.terrakok.cicerone.Router;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;

public class MoviePagePresenter extends MvpPresenter<IMoviePageView> {

    @Inject
    Scheduler scheduler;
    @Inject
    Router router;
    @Inject
    IMovieRepo movieRepo;

    private final String id;
    private final String title;
    private String imageUrl;

    public MoviePagePresenter(String title, String id) {
        this.title = title;
        this.id = id;
        MovieApp.instance.getMovieSubcomponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Logger.logV(null);
        getViewState().init();
        setData();
    }

    @Override
    public void onDestroy() {
        Logger.logV(null);
        super.onDestroy();
        getViewState().release();
    }

    @SuppressLint("CheckResult")
    private void setData() {
        Logger.logV(null);
        movieRepo.getMovie(id).observeOn(scheduler).subscribe(
                (movie) -> {
                    getViewState().setData(title, ViewModelMapper.mapMovie(movie));
                    imageUrl = movie.getImageUrl();
                },
                (e) -> {
                    Logger.logE(String.format("error = %s", e.getMessage()));
                }
        );
    }

    public void onImageClick() {
        if (imageUrl != null) {
            router.navigateTo(new Screens.PosterPageScreen(title, imageUrl));
        }
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
