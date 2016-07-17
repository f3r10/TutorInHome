package com.tnb.f3r10.tutorinhome.tutorlist;

import com.tnb.f3r10.tutorinhome.tutorlist.events.TutorListEvent;

/**
 * Created by f3r10 on 13/7/16.
 */
public interface TutorListPresenter {

    void onCreate();
    void onDestroy();

    /*void subscribe();
    vo id unsubscribe();*/

    void onEventMainThread(TutorListEvent event);

    void getTutors();
    void signOff();
}
