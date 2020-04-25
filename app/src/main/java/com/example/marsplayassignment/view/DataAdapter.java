package com.example.marsplayassignment.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.marsplayassignment.R;
import com.example.marsplayassignment.interfaces.OnItemClickListener;
import com.example.marsplayassignment.model.ImageUrl;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<ImageUrl> imageUrls;
    private Context context;
    private OnItemClickListener onClickListener;

    public DataAdapter(Context context, ArrayList<ImageUrl> imageUrls, OnItemClickListener onClickListener) {
        this.context = context;
        this.imageUrls = imageUrls;
        this.onClickListener = onClickListener;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    /**
     * gets the image url from adapter and passes to Glide API to load the image
     *
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bind(onClickListener, imageUrls.get(i).getImageUrl());
        Glide.with(context).load(imageUrls.get(i).getImageUrl()).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;

        public ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.image);
        }

        public void bind (final OnItemClickListener onClickListener, final String imageUrl) {
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClickListener(imageUrl);
                }
            });
        }
    }
}