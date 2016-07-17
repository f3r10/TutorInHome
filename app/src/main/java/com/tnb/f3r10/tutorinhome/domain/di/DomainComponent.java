package com.tnb.f3r10.tutorinhome.domain.di;

import com.tnb.f3r10.tutorinhome.domain.TutorInHomeAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by f3r10 on 12/7/16.
 */
@Singleton
@Component(modules = {DomainModule.class, TutorInHomeAppModule.class})
public interface DomainComponent {

}
