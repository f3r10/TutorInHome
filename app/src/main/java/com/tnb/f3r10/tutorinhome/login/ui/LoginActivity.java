package com.tnb.f3r10.tutorinhome.login.ui;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.tnb.f3r10.tutorinhome.R;
import com.tnb.f3r10.tutorinhome.TutorInHomeApplication;
import com.tnb.f3r10.tutorinhome.tutorlist.ui.TutorListActivity;
import com.tnb.f3r10.tutorinhome.login.LoginPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class LoginActivity extends AppCompatActivity implements LoginView {

    @Bind(R.id.editTxtEmail)
    EditText inputEmail;

    @Bind(R.id.editTxtPassword)
    EditText inputPassword;

    @Bind(R.id.btnSignin)
    Button btnSignin;
    @Bind(R.id.btnSignup)
    Button btnSignup;
    @Bind(R.id.layoutButtons)
    LinearLayout layoutButtons;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.layoutMainContainer)
    RelativeLayout layoutMainContainer;

    TutorInHomeApplication app;
    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        app = (TutorInHomeApplication)getApplication();
        setUpInjection();
        loginPresenter.onCreate();
        loginPresenter.checkForAuthenticationUser();
    }

    private void setUpInjection() {
        app.getLoginComponent(this).inject(this);
    }


    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnSignup)
    @Override
    public void handleSignUp() {
        loginPresenter.registerNewUser(inputEmail.getText().toString(),
                inputPassword.getText().toString());
    }

    @OnClick(R.id.btnSignin)
    @Override
    public void handleSignIn() {
        loginPresenter.validateLogin(inputEmail.getText().toString(),
                inputPassword.getText().toString());
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this, TutorListActivity.class));
    }

    @Override
    public void loginError(String error) {
        inputPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signin), error);
        inputPassword.setError(msgError);
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(layoutMainContainer, R.string.login_notice_message_signup, Snackbar.LENGTH_SHORT ).show();
    }

    @Override
    public void newUserError(String error) {
        inputPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signup), error);
        inputPassword.setError(msgError);
    }

    private void setInputs(boolean enabled){
        inputEmail.setEnabled(enabled);
        inputPassword.setEnabled(enabled);
        btnSignin.setEnabled(enabled);
        btnSignup.setEnabled(enabled);
    }
}
