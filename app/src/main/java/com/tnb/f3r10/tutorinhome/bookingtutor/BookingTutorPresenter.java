package com.tnb.f3r10.tutorinhome.bookingtutor;

import com.tnb.f3r10.tutorinhome.bookingtutor.ui.events.BookingTutorEvent;
import com.tnb.f3r10.tutorinhome.entities.Booking;

/**
 * Created by f3r10 on 15/7/16.
 */
public interface BookingTutorPresenter {
    void onShow();
    void onDestroy();
    void bookingTutor(Booking booking);
    void onEventMainThread(BookingTutorEvent event);
}
