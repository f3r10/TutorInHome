package com.tnb.f3r10.tutorinhome.domain;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.tnb.f3r10.tutorinhome.TutorInHomeApplication;
import com.tnb.f3r10.tutorinhome.api.AddCookiesInterceptor;
import com.tnb.f3r10.tutorinhome.api.ReceivedCookiesInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by f3r10 on 12/7/16.
 */
@Module
public class TutorInHomeAppModule {

    TutorInHomeApplication app;

    public TutorInHomeAppModule(TutorInHomeApplication app) {
        this.app = app;
    }


    @Provides
    @Singleton
    SharedPreferences povidesSharedPreferences(Application application){
        return application.getSharedPreferences(app.getSharedPreferecesName(), Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    ReceivedCookiesInterceptor providesReceivedCookiesInterceptor(SharedPreferences sharedPreferences){
        return new ReceivedCookiesInterceptor(sharedPreferences);
    }

    @Provides
    @Singleton
    AddCookiesInterceptor providesAddCookiesInterceptor(SharedPreferences sharedPreferences){
        return new AddCookiesInterceptor(sharedPreferences);
    }

    @Provides
    @Singleton
    Context provideApplicationContext(){
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    Application providesApplication(){
        return this.app;
    }
}
