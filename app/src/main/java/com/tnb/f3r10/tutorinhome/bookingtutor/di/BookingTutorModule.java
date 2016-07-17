package com.tnb.f3r10.tutorinhome.bookingtutor.di;

import com.tnb.f3r10.tutorinhome.api.AddCookiesInterceptor;
import com.tnb.f3r10.tutorinhome.api.ReceivedCookiesInterceptor;
import com.tnb.f3r10.tutorinhome.api.TutorInHomeClient;
import com.tnb.f3r10.tutorinhome.api.TutorInHomeService;
import com.tnb.f3r10.tutorinhome.bookingtutor.BookingTutorInteractor;
import com.tnb.f3r10.tutorinhome.bookingtutor.BookingTutorInteractorImpl;
import com.tnb.f3r10.tutorinhome.bookingtutor.BookingTutorPresenter;
import com.tnb.f3r10.tutorinhome.bookingtutor.BookingTutorPresenterImpl;
import com.tnb.f3r10.tutorinhome.bookingtutor.BookingTutorRepository;
import com.tnb.f3r10.tutorinhome.bookingtutor.BookingTutorRepositoryImpl;
import com.tnb.f3r10.tutorinhome.bookingtutor.ui.BookingTutorView;
import com.tnb.f3r10.tutorinhome.lib.base.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by f3r10 on 15/7/16.
 */
@Module
public class BookingTutorModule {
    BookingTutorView view;

    public BookingTutorModule(BookingTutorView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    BookingTutorPresenter providesBookingTutorPresenter(EventBus eventBus, BookingTutorInteractor interactor, BookingTutorView view){
        return new BookingTutorPresenterImpl(eventBus, interactor, view);
    }

    @Provides
    @Singleton
    BookingTutorView providesBookingTutorView(){
        return this.view;
    }

    @Provides
    @Singleton
    BookingTutorInteractor providesBookingTutorInteractor(BookingTutorRepository repository){
        return  new BookingTutorInteractorImpl(repository);
    }

    @Provides
    @Singleton
    BookingTutorRepository providesBookingTutorRepository(EventBus eventBus, TutorInHomeService service){
        return new BookingTutorRepositoryImpl(eventBus, service);
    }

    @Provides
    @Singleton
    TutorInHomeService provideTutorInHomeService(TutorInHomeClient client){
        return client.getTutorInHomeService();
    }

    @Provides
    @Singleton
    TutorInHomeClient providesTutorInHomeClient(ReceivedCookiesInterceptor receivedCookiesInterceptor, AddCookiesInterceptor addCookiesInterceptor){
        return new TutorInHomeClient(receivedCookiesInterceptor, addCookiesInterceptor);
    }
}
