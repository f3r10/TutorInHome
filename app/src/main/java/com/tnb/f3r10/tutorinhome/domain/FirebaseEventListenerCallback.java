package com.tnb.f3r10.tutorinhome.domain;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

/**
 * Created by f3r10 on 12/7/16.
 */
public interface FirebaseEventListenerCallback {
    void onChildAdded(DataSnapshot dataSnapshot);
    void onChildRemoved(DataSnapshot dataSnapshot);
    void onCancelled(FirebaseError error);
}
