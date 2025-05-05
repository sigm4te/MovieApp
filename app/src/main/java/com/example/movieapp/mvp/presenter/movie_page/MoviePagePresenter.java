package com.example.movieapp.mvp.presenter.movie_page;

import android.annotation.SuppressLint;

import com.example.movieapp.mvp.model.repository.IMoviePageRepo;
import com.example.movieapp.mvp.presenter.base.ViewModelMapper;
import com.example.movieapp.mvp.view.movie_page.IMoviePageView;
import com.example.movieapp.utils.log.Logger;
import com.github.terrakok.cicerone.Router;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;

public class MoviePagePresenter extends MvpPresenter<IMoviePageView> {

    private final Scheduler scheduler;
    private final Router router;
    private final IMoviePageRepo moviePageRepo;
    private final String id;

    public MoviePagePresenter(Scheduler scheduler, Router router, IMoviePageRepo moviePageRepo, String id) {
        this.scheduler = scheduler;
        this.router = router;
        this.moviePageRepo = moviePageRepo;
        this.id = id;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Logger.logV(null);
        getViewState().init();
        setData();
    }

    @SuppressLint("CheckResult")
    private void setData() {
        Logger.logV(null);
        moviePageRepo.getMovie(id).observeOn(scheduler).subscribe(
                (movie) -> {
                    getViewState().setData(ViewModelMapper.mapMovie(movie));
                },
                (e) -> {
                    Logger.logE(String.format("error = %s", e.getMessage()));
                }
        );
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
