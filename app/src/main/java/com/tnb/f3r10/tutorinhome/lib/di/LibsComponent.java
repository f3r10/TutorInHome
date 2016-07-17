package com.tnb.f3r10.tutorinhome.lib.di;

import com.tnb.f3r10.tutorinhome.domain.TutorInHomeAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by f3r10 on 13/7/16.
 */
@Singleton
@Component(modules = {LibsModule.class, TutorInHomeAppModule.class})
public interface LibsComponent {
}
