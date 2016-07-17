package com.tnb.f3r10.tutorinhome.bookingtutor.di;

import com.tnb.f3r10.tutorinhome.domain.TutorInHomeAppModule;
import com.tnb.f3r10.tutorinhome.bookingtutor.ui.BookingTutorFragment;
import com.tnb.f3r10.tutorinhome.domain.di.DomainModule;
import com.tnb.f3r10.tutorinhome.lib.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by f3r10 on 15/7/16.
 */
@Singleton
@Component(modules = {BookingTutorModule.class, DomainModule.class, LibsModule.class, TutorInHomeAppModule.class})
public interface BookingTutorComponent {

    void inject(BookingTutorFragment fragment);
}
