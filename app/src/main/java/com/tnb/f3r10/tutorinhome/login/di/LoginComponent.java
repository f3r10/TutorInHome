package com.tnb.f3r10.tutorinhome.login.di;

import com.tnb.f3r10.tutorinhome.domain.TutorInHomeAppModule;
import com.tnb.f3r10.tutorinhome.domain.di.DomainModule;
import com.tnb.f3r10.tutorinhome.lib.di.LibsModule;
import com.tnb.f3r10.tutorinhome.login.ui.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by f3r10 on 13/7/16.
 */
@Singleton
@Component(modules = {LoginModule.class, DomainModule.class, LibsModule.class, TutorInHomeAppModule.class})
public interface LoginComponent {

    void inject(LoginActivity activity);
}
