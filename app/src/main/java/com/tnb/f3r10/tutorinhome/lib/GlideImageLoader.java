package com.tnb.f3r10.tutorinhome.lib;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.tnb.f3r10.tutorinhome.lib.base.ImageLoader;

/**
 * Created by f3r10 on 13/7/16.
 */
public class GlideImageLoader implements ImageLoader {

    private RequestManager glideRequestManager;
    private RequestListener onFinishedImageLoadingListener;

    public GlideImageLoader(RequestManager requestManager) {
        this.glideRequestManager = requestManager;
    }

    @Override
    public void load(ImageView imageView, String URL) {
        if (onFinishedImageLoadingListener != null) {
            glideRequestManager
                    .load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .listener(onFinishedImageLoadingListener)
                    .into(imageView);
        } else {
            glideRequestManager
                    .load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(imageView);
        }
    }

    @Override
    public void setOnFinishedImageLoadingListener(Object listener) {
        try {
            this.onFinishedImageLoadingListener = (RequestListener) listener;
        } catch (ClassCastException e) {
            Log.e(this.getClass().getName(),e.getMessage());
        }
    }
}
