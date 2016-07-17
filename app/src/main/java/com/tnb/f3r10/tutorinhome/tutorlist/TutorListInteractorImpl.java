package com.tnb.f3r10.tutorinhome.tutorlist;

/**
 * Created by f3r10 on 13/7/16.
 */
public class TutorListInteractorImpl implements TutorListInteractor {
    TutorListRepository repository;

    public TutorListInteractorImpl(TutorListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getTutors();
    }
}
