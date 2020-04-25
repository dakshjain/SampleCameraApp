package com.example.marsplayassignment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.marsplayassignment.R;
import com.example.marsplayassignment.databinding.ActivityPreviewImageBinding;

public class PreviewImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPreviewImageBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_preview_image);


        Bundle b = getIntent().getExtras();
        String value = "";
        if(b != null)
            value = b.getString("url");

        Glide.with(this).load(value).into(binding.img);

    }
}
