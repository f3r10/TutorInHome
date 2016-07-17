package com.tnb.f3r10.tutorinhome.login.ui;

/**
 * Created by f3r10 on 13/7/16.
 */
public interface LoginView {

    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleSignUp();
    void handleSignIn();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);
}
