package com.example.grmlogbook.app;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class ImageLoadTarget implements Target {

    private ImageView imageView;

    public ImageLoadTarget(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
        this.imageView.setImageBitmap(bitmap);
        //store your bitmap as you were storing
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {

    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }

}