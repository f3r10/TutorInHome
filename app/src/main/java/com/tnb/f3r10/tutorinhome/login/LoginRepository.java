package com.tnb.f3r10.tutorinhome.login;

/**
 * Created by f3r10 on 13/7/16.
 */
public interface LoginRepository {

    void signUp(String email, String password);
    void signIn(String email, String password);
    void checkSession();
}
