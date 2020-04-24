package com.example.marsplayassignment;

import android.net.Uri;

import javax.inject.Inject;

import io.reactivex.ObservableEmitter;

public class HomePageRepository {
    WebService webService;

    @Inject
    public HomePageRepository(WebService webService) {
        this.webService = webService;
    }

    public Response upload(Uri filepath, ObservableEmitter emitter) {
       return webService.upload(filepath, emitter);
    }
}
