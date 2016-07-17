package com.tnb.f3r10.tutorinhome;

import android.app.Application;

import com.firebase.client.Firebase;
import com.tnb.f3r10.tutorinhome.bookingtutor.di.BookingTutorComponent;
import com.tnb.f3r10.tutorinhome.bookingtutor.di.BookingTutorModule;
import com.tnb.f3r10.tutorinhome.bookingtutor.di.DaggerBookingTutorComponent;
import com.tnb.f3r10.tutorinhome.bookingtutor.ui.BookingTutorView;
import com.tnb.f3r10.tutorinhome.domain.TutorInHomeAppModule;
import com.tnb.f3r10.tutorinhome.domain.di.DomainModule;
import com.tnb.f3r10.tutorinhome.lib.di.LibsModule;
import com.tnb.f3r10.tutorinhome.login.di.DaggerLoginComponent;
import com.tnb.f3r10.tutorinhome.login.di.LoginComponent;
import com.tnb.f3r10.tutorinhome.login.di.LoginModule;
import com.tnb.f3r10.tutorinhome.login.ui.LoginView;
import com.tnb.f3r10.tutorinhome.tutordetail.TutorDetailActivity;
import com.tnb.f3r10.tutorinhome.tutordetail.di.DaggerTutorDetailComponent;
import com.tnb.f3r10.tutorinhome.tutordetail.di.TutorDetailComponent;
import com.tnb.f3r10.tutorinhome.tutordetail.di.TutorDetailModule;
import com.tnb.f3r10.tutorinhome.tutorlist.di.DaggerTutorListComponent;
import com.tnb.f3r10.tutorinhome.tutorlist.di.TutorListComponent;
import com.tnb.f3r10.tutorinhome.tutorlist.di.TutorListModule;
import com.tnb.f3r10.tutorinhome.tutorlist.ui.TutorListActivity;
import com.tnb.f3r10.tutorinhome.tutorlist.ui.TutorListView;
import com.tnb.f3r10.tutorinhome.tutorlist.ui.adapter.OnItemClickListener;

/**
 * Created by f3r10 on 12/7/16.
 */
public class TutorInHomeApplication extends Application {

    private final static String FIREBASE_URL = "https://android-chat-f3r10.firebaseio.com/";
    private final static String TUTOR_KEY = "TUTOR_KEY";
    private static final String BASIC_SUBJECTS_KEY = "BASIC_SUBJECTS_KEY";
    public static final String SHARED_PREFERECES_NAME = "PREF_COOKIES";
    private static final String PROFESIONAL_SUBJECTS_KEY = "PROFESIONAL_SUBJECTS_KEY";
    private static final String TUTOR_ID = "TUTOR_ID";

    public String getTutorKey() {
        return TUTOR_KEY;
    }

    public String getBasicSubjectsKey() {
        return BASIC_SUBJECTS_KEY;
    }

    public String getProfesionalSubjectsKey() {
        return PROFESIONAL_SUBJECTS_KEY;
    }

    public String getTutorId() {
        return TUTOR_ID;
    }

    public String getSharedPreferecesName() {
        return SHARED_PREFERECES_NAME;
    }

    private TutorInHomeAppModule tutorInHomeAppModule;
    private DomainModule domainModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initFirebase();
        initModules();
    }

    private void initModules() {
        tutorInHomeAppModule = new TutorInHomeAppModule(this);
        domainModule = new DomainModule(FIREBASE_URL);
    }

    private void initFirebase() {
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }

    public LoginComponent getLoginComponent(LoginView view){
        return DaggerLoginComponent
                .builder()
                .tutorInHomeAppModule(tutorInHomeAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .loginModule(new LoginModule(view))
                .build();
    }

    public TutorListComponent getTutorListComponent(TutorListView view, OnItemClickListener listener, TutorListActivity activity){
     return DaggerTutorListComponent
             .builder()
             .domainModule(domainModule)
             .tutorInHomeAppModule(tutorInHomeAppModule)
             .libsModule(new LibsModule(activity))
             .tutorListModule(new TutorListModule(view, listener))
             .build();
    }

    public TutorDetailComponent getTutorDetailComponent(TutorDetailActivity activity){
        return DaggerTutorDetailComponent
                .builder()
                .domainModule(domainModule)
                .tutorInHomeAppModule(tutorInHomeAppModule)
                .libsModule(new LibsModule(activity))
                .tutorDetailModule(new TutorDetailModule())
                .build();
    }

    public BookingTutorComponent getBookingTutorComponent(BookingTutorView view){
        return DaggerBookingTutorComponent
                .builder()
                .tutorInHomeAppModule(tutorInHomeAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .bookingTutorModule(new BookingTutorModule(view))
                .build();
    }
}
