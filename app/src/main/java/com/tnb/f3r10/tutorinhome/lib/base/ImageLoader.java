package com.tnb.f3r10.tutorinhome.lib.base;

import android.widget.ImageView;

/**
 * Created by f3r10 on 13/7/16.
 */
public interface ImageLoader {
    void load(ImageView imageView, String URL);
    void setOnFinishedImageLoadingListener(Object object);
}
