package com.tnb.f3r10.tutorinhome.tutorlist.di;

import com.tnb.f3r10.tutorinhome.api.AddCookiesInterceptor;
import com.tnb.f3r10.tutorinhome.api.ReceivedCookiesInterceptor;
import com.tnb.f3r10.tutorinhome.api.TutorInHomeClient;
import com.tnb.f3r10.tutorinhome.api.TutorInHomeService;
import com.tnb.f3r10.tutorinhome.domain.FirebaseAPI;
import com.tnb.f3r10.tutorinhome.domain.Util;
import com.tnb.f3r10.tutorinhome.entities.Tutor;
import com.tnb.f3r10.tutorinhome.lib.base.EventBus;
import com.tnb.f3r10.tutorinhome.lib.base.ImageLoader;
import com.tnb.f3r10.tutorinhome.tutorlist.TutorListInteractor;
import com.tnb.f3r10.tutorinhome.tutorlist.TutorListInteractorImpl;
import com.tnb.f3r10.tutorinhome.tutorlist.TutorListPresenter;
import com.tnb.f3r10.tutorinhome.tutorlist.TutorListPresenterImpl;
import com.tnb.f3r10.tutorinhome.tutorlist.TutorListRepository;
import com.tnb.f3r10.tutorinhome.tutorlist.TutorListRepositoryImpl;
import com.tnb.f3r10.tutorinhome.tutorlist.TutorListSessionInteractor;
import com.tnb.f3r10.tutorinhome.tutorlist.TutorListSessionInteractorImpl;
import com.tnb.f3r10.tutorinhome.tutorlist.ui.TutorListView;
import com.tnb.f3r10.tutorinhome.tutorlist.ui.adapter.OnItemClickListener;
import com.tnb.f3r10.tutorinhome.tutorlist.ui.adapter.TutorListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by f3r10 on 13/7/16.
 */
@Module
public class TutorListModule {
    TutorListView view;
    OnItemClickListener listener;

    public TutorListModule(TutorListView view, OnItemClickListener listener) {
        this.view = view;
        this.listener = listener;
    }

    @Provides
    @Singleton
    TutorListView providesTutorListView(){
        return this.view;
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.listener;
    }

    @Provides
    @Singleton
    TutorListPresenter providesTutorListPresenter(EventBus eventBus, TutorListView view, TutorListInteractor interactor, TutorListSessionInteractor sessionInteractor){
        return new TutorListPresenterImpl(eventBus,view, interactor, sessionInteractor);
    }

    @Provides
    @Singleton
    TutorListSessionInteractor providesSessionInteractor(TutorListRepository repository){
        return new TutorListSessionInteractorImpl(repository);
    }

    @Provides
    @Singleton
    TutorListInteractor providesTutorListInteractor(TutorListRepository repository){
        return new TutorListInteractorImpl(repository);
    }

    @Provides
    @Singleton
    TutorListRepository providesTutorListRepository(EventBus eventBus, TutorInHomeService service, FirebaseAPI firebaseAPI){
        return new TutorListRepositoryImpl(eventBus, service,firebaseAPI);
    }

    @Provides
    @Singleton
    TutorInHomeService provideTutorInHomeService(TutorInHomeClient client){
        return client.getTutorInHomeService();
    }

    @Provides
    @Singleton
    TutorInHomeClient providesTutorInHomeClient(ReceivedCookiesInterceptor receivedCookiesInterceptor, AddCookiesInterceptor addCookiesInterceptor){
        return new TutorInHomeClient(receivedCookiesInterceptor,addCookiesInterceptor);
    }

    @Provides
    @Singleton
    TutorListAdapter providesTutorListAdapter(List<Tutor> tutorList, ImageLoader imageLoader, OnItemClickListener onItemClickListener, Util util){
        return new TutorListAdapter(tutorList, imageLoader, onItemClickListener, util);
    }

    @Provides
    @Singleton
    List<Tutor> providesTutorList(){
        return new ArrayList<Tutor>();
    }

}
