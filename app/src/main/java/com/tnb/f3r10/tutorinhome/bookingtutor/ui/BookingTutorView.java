package com.tnb.f3r10.tutorinhome.bookingtutor.ui;

/**
 * Created by f3r10 on 15/7/16.
 */
public interface BookingTutorView {

    void showProgress();
    void hideProgress();
    void showInput();
    void hideInput();
    void successBookingTutor();
    void errorBookingTutor();
}
