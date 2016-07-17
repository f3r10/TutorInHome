package com.tnb.f3r10.tutorinhome.login;

import com.tnb.f3r10.tutorinhome.login.events.LoginEvent;

/**
 * Created by f3r10 on 13/7/16.
 */
public interface LoginPresenter {

    void onCreate();
    void onDestroy();
    void checkForAuthenticationUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);

    void onEventMainThread(LoginEvent event);
}
