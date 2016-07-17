package com.tnb.f3r10.tutorinhome.tutorlist;

import com.tnb.f3r10.tutorinhome.lib.base.EventBus;
import com.tnb.f3r10.tutorinhome.tutorlist.events.TutorListEvent;
import com.tnb.f3r10.tutorinhome.tutorlist.ui.TutorListView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by f3r10 on 13/7/16.
 */
public class TutorListPresenterImpl implements TutorListPresenter {
    EventBus eventBus;
    TutorListView view;
    TutorListInteractor interactor;
    TutorListSessionInteractor sessionInteractor;

    public TutorListPresenterImpl(EventBus eventBus, TutorListView view, TutorListInteractor interactor, TutorListSessionInteractor sessionInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
        this.sessionInteractor = sessionInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        eventBus.unregister(this);

    }

    @Override
    @Subscribe
    public void onEventMainThread(TutorListEvent event) {
        if(this.view != null){
            switch (event.getType()){
                case TutorListEvent.READ_EVENT:
                    view.hideProgress();
                    view.setTutors(event.getTutors());
                    break;
                case TutorListEvent.ERROR_EVENT:
                    view.hideProgress();
                    view.showError(event.getError());
                    break;
            }
        }
    }

    @Override
    public void getTutors() {
        if( view != null){
            view.showProgress();
        }
        interactor.execute();
    }

    @Override
    public void signOff() {
        sessionInteractor.signOff();
    }

    public TutorListView getView() {
        return view;
    }
}
