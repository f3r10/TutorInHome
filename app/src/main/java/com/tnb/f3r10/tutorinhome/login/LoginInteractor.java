package com.tnb.f3r10.tutorinhome.login;

/**
 * Created by f3r10 on 13/7/16.
 */
public interface LoginInteractor {

    void checkSession();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);
}
