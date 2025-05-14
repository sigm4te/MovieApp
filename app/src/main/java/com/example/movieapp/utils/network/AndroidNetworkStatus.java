package com.example.movieapp.utils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

import androidx.annotation.NonNull;

import com.example.movieapp.mvp.model.network.INetworkStatus;
import com.example.movieapp.app.MovieApp;
import com.example.movieapp.utils.log.Logger;

public class AndroidNetworkStatus implements INetworkStatus {

    private final BehaviorSubject<Boolean> statusObject = BehaviorSubject.create();

    public AndroidNetworkStatus() {
        statusObject.onNext(false);
        init();
    }

    public void init() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MovieApp.instance.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkRequest networkRequest = new NetworkRequest.Builder().build();

        connectivityManager.registerNetworkCallback(networkRequest, new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                Logger.logV(null);
                statusObject.onNext(true);
            }

            @Override
            public void onUnavailable() {
                super.onUnavailable();
                Logger.logV(null);
                statusObject.onNext(false);
            }

            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
                Logger.logV(null);
                statusObject.onNext(false);
            }
        });
    }

    @Override
    public Observable<Boolean> isOnline() {
        return statusObject;
    }

    @Override
    public Single<Boolean> isOnlineSingle() {
        return statusObject.first(false);
    }
}
