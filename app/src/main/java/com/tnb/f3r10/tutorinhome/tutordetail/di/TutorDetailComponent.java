package com.tnb.f3r10.tutorinhome.tutordetail.di;

import com.tnb.f3r10.tutorinhome.domain.TutorInHomeAppModule;
import com.tnb.f3r10.tutorinhome.domain.di.DomainModule;
import com.tnb.f3r10.tutorinhome.lib.di.LibsModule;
import com.tnb.f3r10.tutorinhome.tutordetail.TutorDetailActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by f3r10 on 14/7/16.
 */
@Singleton
@Component(modules = {TutorDetailModule.class, DomainModule.class, LibsModule.class, TutorInHomeAppModule.class})
public interface TutorDetailComponent {

    void inject(TutorDetailActivity activity);
}
