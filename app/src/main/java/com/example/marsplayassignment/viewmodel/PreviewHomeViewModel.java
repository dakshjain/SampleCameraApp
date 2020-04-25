package com.example.marsplayassignment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.marsplayassignment.model.ImageUrl;
import com.example.marsplayassignment.repository.PreviewRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class PreviewHomeViewModel extends AndroidViewModel {

    PreviewRepository previewRepository;
    public MutableLiveData<ArrayList<ImageUrl>> imageUrlObserver = new MutableLiveData<>();
    public ArrayList<ImageUrl> imageUrls = new ArrayList<>();

    @Inject
    public PreviewHomeViewModel(@NonNull Application application, PreviewRepository previewRepository) {
        super(application);
        this.previewRepository = previewRepository;
    }

    public void setImageUrls() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                previewRepository.downloadUrl(emitter);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribeWith(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String downloadResponse) {
                        imageUrls.add(new ImageUrl(downloadResponse));

                        imageUrlObserver.postValue(imageUrls);
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
