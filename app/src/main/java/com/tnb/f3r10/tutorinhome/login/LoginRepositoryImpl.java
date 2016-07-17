package com.tnb.f3r10.tutorinhome.login;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.tnb.f3r10.tutorinhome.domain.FirebaseAPI;
import com.tnb.f3r10.tutorinhome.entities.User;
import com.tnb.f3r10.tutorinhome.lib.base.EventBus;
import com.tnb.f3r10.tutorinhome.login.events.LoginEvent;



import java.util.Map;

/**
 * Created by f3r10 on 13/7/16.
 */
public class LoginRepositoryImpl implements LoginRepository {
    private EventBus eventBus;
    private FirebaseAPI helper;



    public LoginRepositoryImpl(FirebaseAPI helper, EventBus eventBus) {
        this.eventBus = eventBus;
        this.helper = helper;

    }

    @Override
    public void signUp(final String email, final String password) {
        helper.getFirebase().createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                postEvent(LoginEvent.onSignUpSuccess);
                signIn(email,password);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSignUpError, firebaseError.getMessage());
            }
        });

    }

    @Override
    public void signIn(String email, String password) {
        helper.getFirebase().authWithPassword(email, password, new Firebase.AuthResultHandler(){

            @Override
            public void onAuthenticated(AuthData authData) {
                initSignIn();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSignInError, firebaseError.getMessage());
            }
        });
    }

    @Override
    public void checkSession() {
        if(helper.getFirebase().getAuth() != null){
            initSignIn();
        }else{
            postEvent(LoginEvent.onFailedToRecoverSession);
        }

    }

    private void initSignIn(){
        helper.getMyUserReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User currentUser = dataSnapshot.getValue(User.class);
                if(currentUser != null){
                    registerNewUser();
                }
                helper.changeUserConnectionStatus(User.ONLINE);
                postEvent(LoginEvent.onSignInSuccess);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void registerNewUser(){
        String email = helper.getAuthUserEmail();
        if(email != null){
            User currentUser = new User();
            currentUser.setEmail(email);
            helper.getMyUserReference().setValue(currentUser);
        }
    }

    private void postEvent(int type, String errorMessage){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if(errorMessage != null){
            loginEvent.setErrorMesage(errorMessage);
        }

        eventBus.post(loginEvent);

    }

    private void postEvent(int type){
        postEvent(type, null);
    }
}

