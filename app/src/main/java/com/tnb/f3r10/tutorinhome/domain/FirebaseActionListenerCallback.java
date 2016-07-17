package com.tnb.f3r10.tutorinhome.domain;

import com.firebase.client.FirebaseError;

/**
 * Created by f3r10 on 12/7/16.
 */
public interface FirebaseActionListenerCallback {

    void onSuccess();
    void onError(FirebaseError error);
}
