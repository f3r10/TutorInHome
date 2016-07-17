package com.tnb.f3r10.tutorinhome.lib.di;

import android.app.Activity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.tnb.f3r10.tutorinhome.lib.GlideImageLoader;
import com.tnb.f3r10.tutorinhome.lib.GreenRobotEventBus;
import com.tnb.f3r10.tutorinhome.lib.base.EventBus;
import com.tnb.f3r10.tutorinhome.lib.base.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by f3r10 on 13/7/16.
 */
@Module
public class LibsModule {

    private Activity activity;

    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Activity activity){
        return Glide.with(activity);
    }

    @Provides
    @Singleton
    Activity privdesActivity(){
        return this.activity;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }


}
