package com.tnb.f3r10.tutorinhome.domain.di;

import com.firebase.client.Firebase;
import com.tnb.f3r10.tutorinhome.domain.FirebaseAPI;
import com.tnb.f3r10.tutorinhome.domain.Util;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by f3r10 on 12/7/16.
 */
@Module
public class DomainModule {
    String firebaseURL;

    public DomainModule(String firebaseURL) {
        this.firebaseURL = firebaseURL;
    }

    @Provides
    @Singleton
    FirebaseAPI providesFirebaseAPI(Firebase firebase){
        return new FirebaseAPI(firebase);
    }

    @Provides
    @Singleton
    Firebase providesFirebase(String firebaseURL){
        return new Firebase(firebaseURL);
    }

    @Provides
    @Singleton
    String providesFirebaseURL(){
        return this.firebaseURL;
    }

    @Provides
    @Singleton
    Util providesUtil(){
        return new Util();
    }
}
