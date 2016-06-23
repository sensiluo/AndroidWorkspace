package com.example.screens.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.screens.R;

/**
 * Created by H_P on 2016/4/11.
 */
public class HeterogenousImageViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;

    public HeterogenousImageViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.layout_image_container);
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
