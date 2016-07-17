package com.tnb.f3r10.tutorinhome.bookingtutor.ui.events;

/**
 * Created by f3r10 on 15/7/16.
 */
public class BookingTutorEvent {
    boolean error = false;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
