package com.example.marsplayassignment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.marsplayassignment.model.ImageUrl;
import com.example.marsplayassignment.interfaces.OnItemClickListener;
import com.example.marsplayassignment.viewmodel.PreviewHomeViewModel;
import com.example.marsplayassignment.R;
import com.example.marsplayassignment.databinding.ActivityPreviewBinding;
import com.example.marsplayassignment.di.ViewModelFactory;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class PreviewActivity extends AppCompatActivity {

    PreviewHomeViewModel previewHomeViewModel;

    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        final ActivityPreviewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_preview);
        previewHomeViewModel = ViewModelProviders.of(this, viewModelFactory).get(PreviewHomeViewModel.class);

        previewHomeViewModel.setImageUrls();

        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        final DataAdapter dataAdapter = new DataAdapter(this, previewHomeViewModel.imageUrls, new OnItemClickListener() {
            @Override
            public void onClickListener(String imageUrl) {
                Intent intent = new Intent(PreviewActivity.this, PreviewImageActivity.class);
                Bundle b = new Bundle();
                b.putString("url", imageUrl); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
        });

        previewHomeViewModel.imageUrlObserver.observe(this, new Observer<ArrayList<ImageUrl>>() {
            @Override
            public void onChanged(ArrayList<ImageUrl> imageUrls) {

                binding.recyclerView.setAdapter(dataAdapter);
            }
        });


        binding.recyclerView.setAdapter(dataAdapter);

    }
}

