package com.tnb.f3r10.tutorinhome.api;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.tnb.f3r10.tutorinhome.TutorInHomeApplication;

import java.io.IOException;
import java.util.HashSet;
import java.util.prefs.Preferences;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by f3r10 on 15/7/16.
 *
 * credits to http://tsuharesu.com/handling-cookies-with-okhttp/
 */

public class ReceivedCookiesInterceptor implements Interceptor {
    SharedPreferences sharedPreferences;

    public ReceivedCookiesInterceptor(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public ReceivedCookiesInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            editor.putStringSet(TutorInHomeApplication.SHARED_PREFERECES_NAME, cookies).apply();

        }

        return originalResponse;
    }
}
