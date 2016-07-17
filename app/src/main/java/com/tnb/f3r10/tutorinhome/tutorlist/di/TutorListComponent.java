package com.tnb.f3r10.tutorinhome.tutorlist.di;

import com.tnb.f3r10.tutorinhome.domain.TutorInHomeAppModule;
import com.tnb.f3r10.tutorinhome.domain.di.DomainModule;
import com.tnb.f3r10.tutorinhome.lib.di.LibsModule;
import com.tnb.f3r10.tutorinhome.tutorlist.TutorListPresenter;
import com.tnb.f3r10.tutorinhome.tutorlist.ui.adapter.TutorListAdapter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by f3r10 on 13/7/16.
 */
@Singleton
@Component(modules = {TutorListModule.class, DomainModule.class, LibsModule.class, TutorInHomeAppModule.class})
public interface TutorListComponent {
    //void inject(TutorListActivity activity);

    TutorListPresenter getTutorListPresenter();
    TutorListAdapter getTutorListAdapter();
}
