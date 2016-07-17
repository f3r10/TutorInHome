package com.tnb.f3r10.tutorinhome.bookingtutor;

import com.tnb.f3r10.tutorinhome.entities.Booking;

/**
 * Created by f3r10 on 15/7/16.
 */
public class BookingTutorInteractorImpl implements BookingTutorInteractor {
    private BookingTutorRepository repository;

    public BookingTutorInteractorImpl(BookingTutorRepository repository) {
        this.repository = repository;
    }

    @Override
    public void bookingTutor(Booking booking) {
        repository.bookingTutor(booking);
    }
}
