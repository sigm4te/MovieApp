package com.example.movieapp.di;

import com.example.movieapp.di.base.ApiModule;
import com.example.movieapp.di.base.AppModule;
import com.example.movieapp.di.base.CiceroneModule;
import com.example.movieapp.di.search_page.SearchPageSubcomponent;
import com.example.movieapp.mvp.presenter.base.MainPresenter;
import com.example.movieapp.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        ApiModule.class,
        CiceroneModule.class
})
public interface AppComponent {

    SearchPageSubcomponent searchPageSubcomponent();

    void inject(MainActivity mainActivity);
    void inject(MainPresenter mainPresenter);
}
