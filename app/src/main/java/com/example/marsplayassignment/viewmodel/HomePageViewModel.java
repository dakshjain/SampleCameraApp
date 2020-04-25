package com.example.marsplayassignment.viewmodel;

import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.marsplayassignment.repository.HomePageRepository;
import com.example.marsplayassignment.model.Response;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePageViewModel extends ViewModel {

    HomePageRepository homePageRepository;
    public MutableLiveData uploadStatus = new MutableLiveData();

    @Inject
    public HomePageViewModel(HomePageRepository homePageRepository) {
        this.homePageRepository = homePageRepository;
    }

    public void uploadToFirebase(final Uri filepath){
        uploadStatus.postValue(Response.LOADING);
        Observable.create(new ObservableOnSubscribe<Response>() {
                    @Override
                    public void subscribe(ObservableEmitter<Response> emitter) {
                        homePageRepository.upload(filepath, emitter);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribeWith(new Observer<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response response) {
                        if(response.equals(Response.SUCCESS)) {
                            Log.d("ViewModel", "SUCCESSS");
                            uploadStatus.postValue(response);
                        }
                        if(response.equals(Response.ERROR)) {
                            Log.d("ViewModel", "ERROR");
                            uploadStatus.postValue(response);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}


