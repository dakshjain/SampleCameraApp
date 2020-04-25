package com.example.marsplayassignment.repository;

import com.example.marsplayassignment.network.WebService;

import javax.inject.Inject;

import io.reactivex.ObservableEmitter;

public class PreviewRepository {

    WebService webService;

    @Inject
    public PreviewRepository(WebService webService) {
        this.webService = webService;
    }

    public void downloadUrl(ObservableEmitter emitter) {
        webService.download(emitter);
    }
}

