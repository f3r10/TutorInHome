package com.tnb.f3r10.tutorinhome.api;

import android.util.Log;

import com.tnb.f3r10.tutorinhome.BuildConfig;

import java.io.IOException;
import java.net.CookieManager;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by f3r10 on 13/7/16.
 */
public class TutorInHomeClient {

    private Retrofit retrofit;
    private final static String BASE_URL = "http://www.tutorinhome.com/api/";

    public TutorInHomeClient(ReceivedCookiesInterceptor receivedCookiesInterceptor, AddCookiesInterceptor addCookiesInterceptor) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        httpClientBuilder.addInterceptor(addCookiesInterceptor);
        httpClientBuilder.addInterceptor(receivedCookiesInterceptor);
        httpClientBuilder.addInterceptor(logging);
        OkHttpClient customOkHttpClient = httpClientBuilder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(customOkHttpClient)
                .build();
    }

    public TutorInHomeClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public TutorInHomeService getTutorInHomeService() {
        return retrofit.create(TutorInHomeService.class);
    }
}


