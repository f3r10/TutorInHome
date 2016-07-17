package com.tnb.f3r10.tutorinhome.domain;


import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.tnb.f3r10.tutorinhome.entities.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by f3r10 on 12/7/16.
 */
public class FirebaseAPI {


    private Firebase firebase;


    private final static String USERS_PATH = "users";
    private final static String CHATS_PATH = "chats";
    private final static String CONTACTS_PATH = "contacts";
    private final static String SEPARATOR = "___";

    public FirebaseAPI(Firebase firebase) {
        this.firebase = firebase;
    }

    public Firebase getFirebase(){
        return firebase;
    }

    public String getAuthUserEmail(){
        AuthData authData = firebase.getAuth();

        String email = null;
        if(authData != null){
            Map<String, Object> providerData = authData.getProviderData();
            email = providerData.get("email").toString();
        }

        return email;
    }

    public Firebase getUserReference(String email){
        Firebase userReference = null;

        if(email != null){
            String emailKey = email.replace(".", "_");
            userReference = firebase.getRoot().child(USERS_PATH).child(emailKey);
        }

        return userReference;
    }

    public Firebase getMyUserReference(){
        return getUserReference(getAuthUserEmail());
    }

    public Firebase getContactsReference(String email){
        return getUserReference(email).child(CONTACTS_PATH);
    }

    public Firebase getMyContactsReference(){
        return getContactsReference(getAuthUserEmail());
    }

    public Firebase getOneContactReference(String mainEmail, String childEmail){
        String childKey = childEmail.replace(".","_");
        return getUserReference(mainEmail).child(CONTACTS_PATH).child(childKey);
    }

    public Firebase getChatsReference(String receiver){
        String keySender = getAuthUserEmail().replace(".","_");
        String keyReceiver = receiver.replace(".","_");

        String keyChat = keySender + SEPARATOR + keyReceiver;
        if (keySender.compareTo(keyReceiver) > 0) {
            keyChat = keyReceiver + SEPARATOR + keySender;
        }
        return firebase.getRoot().child(CHATS_PATH).child(keyChat);
    }

    public void changeUserConnectionStatus(boolean online) {
        if (getMyUserReference() != null) {
            Map<String, Object> updates = new HashMap<String, Object>();
            updates.put("online", online);
            getMyUserReference().updateChildren(updates);

            notifyContactsOfConnectionChange(online);
        }
    }

    public void notifyContactsOfConnectionChange(final boolean online, final boolean signoff) {
        final String myEmail = getAuthUserEmail();
        getMyContactsReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    String email = child.getKey();
                    Firebase reference = getOneContactReference(email, myEmail);
                    reference.setValue(online);
                }
                if (signoff){
                    firebase.unauth();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    public void notifyContactsOfConnectionChange(boolean online) {
        notifyContactsOfConnectionChange(online, false);
    }


    public void signOff(){
        //FIXME Ready for the next version
        //notifyContactsOfConnectionChange(User.OFFLINE, true);
        //notifyContactsOfConnectionChange(false, true);
        firebase.unauth();

    }

}
