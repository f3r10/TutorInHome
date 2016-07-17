package com.tnb.f3r10.tutorinhome.login;

import com.tnb.f3r10.tutorinhome.lib.base.EventBus;
import com.tnb.f3r10.tutorinhome.login.events.LoginEvent;
import com.tnb.f3r10.tutorinhome.login.ui.LoginView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by f3r10 on 13/7/16.
 */
public class LoginPresenterImpl implements LoginPresenter {
    private LoginView loginView;
    private EventBus eventBus;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView, EventBus eventBus, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.eventBus = eventBus;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
        eventBus.unregister(this);
    }

    @Override
    public void checkForAuthenticationUser() {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }

        loginInteractor.checkSession();
    }

    @Override
    public void validateLogin(String email, String password) {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }

        loginInteractor.doSignIn(email, password);
    }

    @Override
    public void registerNewUser(String email, String password) {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgress();
        }

        loginInteractor.doSignUp(email, password);
    }


    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()){
            case LoginEvent.onSignInSuccess:
                onSignInSuccess();
                break;
            case LoginEvent.onSignInError:
                onSignInError(event.getErrorMesage());
                break;
            case LoginEvent.onSignUpSuccess:
                onSignUpSuccess();
                break;
            case LoginEvent.onSignUpError:
                onSignUpErorr(event.getErrorMesage());
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
        }
    }

    private void onFailedToRecoverSession() {
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
        }
    }

    private void onSignInSuccess(){
        if(loginView != null){
            loginView.navigateToMainScreen();
        }
    }

    private void onSignUpSuccess(){
        if(loginView != null){
            loginView.newUserSuccess();
        }
    }

    private void onSignInError(String error){
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.loginError(error);
        }
    }

    private void onSignUpErorr(String error){
        if(loginView != null){
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.newUserError(error);
        }
    }
}

