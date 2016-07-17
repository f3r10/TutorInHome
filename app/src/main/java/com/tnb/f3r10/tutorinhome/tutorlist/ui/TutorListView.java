package com.tnb.f3r10.tutorinhome.tutorlist.ui;

import com.tnb.f3r10.tutorinhome.entities.Tutor;

import java.util.List;

/**
 * Created by f3r10 on 13/7/16.
 */
public interface TutorListView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();
    void showError(String msg);

    void setTutors(List<Tutor> data);


}
