package com.example.marsplayassignment.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.marsplayassignment.viewmodel.HomePageViewModel;
import com.example.marsplayassignment.R;
import com.example.marsplayassignment.databinding.ActivityCameraViewBinding;
import com.otaliastudios.cameraview.BitmapCallback;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.PictureResult;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class CameraView extends AppCompatActivity {

    final int CROP_PIC = 2;

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private HomePageViewModel homePageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        final ActivityCameraViewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_camera_view);
        binding.cameraView.setLifecycleOwner(this);
        homePageViewModel = new ViewModelProvider(this, viewModelFactory).get(HomePageViewModel.class);

        checkPermission();
        binding.btnSnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.cameraView.takePicture();
            }
        });

        binding.cameraView.addCameraListener(new CameraListener() {

            @Override
            public void onPictureTaken(@NonNull PictureResult result) {
                super.onPictureTaken(result);

                result.toBitmap(new BitmapCallback() {
                    @Override
                    public void onBitmapReady(@Nullable Bitmap bitmap) {
                        Uri uri =  getImageUri(CameraView.this, bitmap);
                        performCrop(uri);
                    }
                });
            }
        });
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void performCrop(Uri picUri) {

        try {

            Intent cropIntent = new Intent("com.android.camera.action.CROP");

            cropIntent.setDataAndType(picUri, "image/*");

            cropIntent.putExtra("crop", "true");

            cropIntent.putExtra("return-data", true);

            startActivityForResult(cropIntent, CROP_PIC);
        }
        catch (ActivityNotFoundException anfe) {
            Toast toast = Toast
                    .makeText(this, "This device doesn't support the crop action!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

             if (requestCode == CROP_PIC) {
                Bundle extras = data.getExtras();

                Bitmap thePic = extras.getParcelable("data");
                 Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();

                 Uri picUri = getImageUri(CameraView.this, thePic);
                 homePageViewModel.uploadToFirebase(picUri);
            }
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(CameraView.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermission();
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(CameraView.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(CameraView.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(CameraView.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted");
                } else {
                    Log.e("value", "Permission Denied");
                }
                break;
        }
    }
}
