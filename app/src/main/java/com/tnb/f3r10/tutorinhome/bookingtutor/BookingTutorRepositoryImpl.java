package com.tnb.f3r10.tutorinhome.bookingtutor;

import com.tnb.f3r10.tutorinhome.api.TutorInHomeService;
import com.tnb.f3r10.tutorinhome.bookingtutor.ui.events.BookingTutorEvent;
import com.tnb.f3r10.tutorinhome.domain.FirebaseAPI;
import com.tnb.f3r10.tutorinhome.entities.Booking;
import com.tnb.f3r10.tutorinhome.lib.base.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by f3r10 on 15/7/16.
 */
public class BookingTutorRepositoryImpl implements BookingTutorRepository {
    EventBus eventBus;
    TutorInHomeService service;

    public BookingTutorRepositoryImpl(EventBus eventBus, TutorInHomeService service) {
        this.eventBus = eventBus;
        this.service = service;
    }

    @Override
    public void bookingTutor(Booking booking) {
        Call<Booking> call = service.bookingTutor(booking);
        call.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                postSuccess();
            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                postError();
            }
        });
    }

    private void postSuccess() {
        post(false);
    }

    private void postError(){
        post(true);
    }

    private void post(boolean error){
        BookingTutorEvent event = new BookingTutorEvent();
        event.setError(error);
        eventBus.post(event);
    }
}
