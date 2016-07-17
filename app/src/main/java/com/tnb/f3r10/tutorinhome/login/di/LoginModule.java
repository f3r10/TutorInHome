package com.tnb.f3r10.tutorinhome.login.di;

import com.tnb.f3r10.tutorinhome.domain.FirebaseAPI;
import com.tnb.f3r10.tutorinhome.lib.base.EventBus;
import com.tnb.f3r10.tutorinhome.login.LoginInteractor;
import com.tnb.f3r10.tutorinhome.login.LoginInteractorImpl;
import com.tnb.f3r10.tutorinhome.login.LoginPresenter;
import com.tnb.f3r10.tutorinhome.login.LoginPresenterImpl;
import com.tnb.f3r10.tutorinhome.login.LoginRepository;
import com.tnb.f3r10.tutorinhome.login.LoginRepositoryImpl;
import com.tnb.f3r10.tutorinhome.login.ui.LoginView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by f3r10 on 13/7/16.
 */
@Module
public class LoginModule {
    LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    LoginView providesLoginView(){
        return this.view;
    }

    @Provides
    @Singleton
    LoginPresenter providesLoginPresenter(LoginView loginView, EventBus eventBus, LoginInteractor loginInteractor){
        return new LoginPresenterImpl(loginView,eventBus,loginInteractor);
    }

    @Provides
    @Singleton
    LoginInteractor providesLoginInteractor(LoginRepository loginRepository){
        return new LoginInteractorImpl(loginRepository);
    }

    @Provides
    @Singleton
    LoginRepository providesLoginRepository(FirebaseAPI helper, EventBus eventBus){
        return new LoginRepositoryImpl(helper,eventBus);
    }
}
