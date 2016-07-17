package com.tnb.f3r10.tutorinhome.login;

/**
 * Created by f3r10 on 13/7/16.
 */
public class LoginInteractorImpl implements LoginInteractor {
    private LoginRepository loginRepository;

    public LoginInteractorImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public void checkSession() {
        loginRepository.checkSession();
    }

    /**
     *
     * @param email
     * @param password
     */
    @Override
    public void doSignUp(String email, String password) {
        loginRepository.signUp(email, password);
    }

    @Override
    public void doSignIn(String email, String password) {
        loginRepository.signIn(email, password);
    }
}

