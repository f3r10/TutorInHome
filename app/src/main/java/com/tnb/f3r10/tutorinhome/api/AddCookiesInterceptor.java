package com.tnb.f3r10.tutorinhome.api;

import android.content.SharedPreferences;
import android.util.Log;

import com.tnb.f3r10.tutorinhome.TutorInHomeApplication;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by f3r10 on 16/7/16.
 *
 * credits to http://tsuharesu.com/handling-cookies-with-okhttp/
 */
public class AddCookiesInterceptor implements Interceptor {

    SharedPreferences sharedPreferences;

    public AddCookiesInterceptor(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public AddCookiesInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences = (HashSet) sharedPreferences.getStringSet(TutorInHomeApplication.SHARED_PREFERECES_NAME, new HashSet<String>());
        for (String cookie : preferences) {
            if(cookie.contains("XSRF-TOKEN")){
                String token = cookie.split(";")[0].split("=")[1];
                builder.header("X-XSRF-Token", URLDecoder.decode(token, "UTF-8"));
            }else{
                String cleanCookie = cookie.split(";")[0].split("=")[1];
                //builder.header("cookie", URLDecoder.decode(cleanCookie, "UTF-8"));
            }


        }

        return chain.proceed(builder.build());
    }
}
