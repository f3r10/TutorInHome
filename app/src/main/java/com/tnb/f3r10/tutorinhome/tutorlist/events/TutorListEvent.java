package com.tnb.f3r10.tutorinhome.tutorlist.events;

import com.tnb.f3r10.tutorinhome.entities.Tutor;

import java.util.List;

/**
 * Created by f3r10 on 13/7/16.
 */
public class TutorListEvent {
    private int type;
    private List<Tutor> tutors;
    private String error;
    public final static int READ_EVENT = 0;
    public final static int UPDATE_EVENT = 1;
    public final static int DELETE_EVENT = 2;
    public final static int ERROR_EVENT = 3;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Tutor> getTutors() {
        return tutors;
    }

    public void setTutors(List<Tutor> tutors) {
        this.tutors = tutors;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
