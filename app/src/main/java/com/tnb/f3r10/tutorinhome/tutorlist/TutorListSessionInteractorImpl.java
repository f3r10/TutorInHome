package com.tnb.f3r10.tutorinhome.tutorlist;

/**
 * Created by f3r10 on 17/7/16.
 */
public class TutorListSessionInteractorImpl implements TutorListSessionInteractor {
    TutorListRepository repository;

    public TutorListSessionInteractorImpl(TutorListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void signOff() {
        repository.signOff();
    }
}
