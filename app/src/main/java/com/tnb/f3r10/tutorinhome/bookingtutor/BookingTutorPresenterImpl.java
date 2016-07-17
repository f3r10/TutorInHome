package com.tnb.f3r10.tutorinhome.bookingtutor;

import com.tnb.f3r10.tutorinhome.bookingtutor.ui.BookingTutorView;
import com.tnb.f3r10.tutorinhome.bookingtutor.ui.events.BookingTutorEvent;
import com.tnb.f3r10.tutorinhome.entities.Booking;
import com.tnb.f3r10.tutorinhome.lib.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by f3r10 on 15/7/16.
 */
public class BookingTutorPresenterImpl implements BookingTutorPresenter {
    private EventBus eventBus;
    private BookingTutorInteractor interactor;
    private BookingTutorView view;

    public BookingTutorPresenterImpl(EventBus eventBus, BookingTutorInteractor interactor, BookingTutorView view) {
        this.eventBus = eventBus;
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);

    }

    @Override
    public void bookingTutor(Booking booking) {
        if(view != null){
            view.hideInput();
            view.showProgress();
        }

        interactor.bookingTutor(booking);

    }

    @Override
    @Subscribe
    public void onEventMainThread(BookingTutorEvent event) {
        if(view != null){
            view.hideProgress();
            view.showInput();
        }
        if(event.isError()){
            view.errorBookingTutor();
        }else{
            view.successBookingTutor();
        }
    }
}
