package com.example.marsplayassignment.network;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.marsplayassignment.model.Response;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.UUID;

import io.reactivex.ObservableEmitter;


public class WebService {
    FirebaseStorage storage;
    StorageReference storageReference;

    ArrayList<String> strings = new ArrayList<>();

    public WebService() {
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        strings.add("images/2439c33d-8503-4c6b-a5f0-5b5e72c46d99");
        strings.add("images/3cb09bc5-fbfc-468c-89fa-82900f205cae");
    }

    public Response upload(Uri filepath, final ObservableEmitter<Response> emitter) {
        Response response = Response.SUCCESS;
        String uri = "images/"+ UUID.randomUUID().toString();
        strings.add(uri);
        StorageReference ref = storageReference.child(uri);
        ref.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        emitter.onNext(Response.SUCCESS);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        emitter.onNext(Response.ERROR);
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                .getTotalByteCount());
                    }
                });
        return response;
    }

    public void download(final ObservableEmitter<String> emitter) {

        StorageReference ref = storageReference;
        for (String string : strings) {
            ref.child(string).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    emitter.onNext(uri.toString());
                }
            });
        }
    }
}
