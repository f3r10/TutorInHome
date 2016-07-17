package com.tnb.f3r10.tutorinhome.tutorlist;

import android.util.Log;

import com.tnb.f3r10.tutorinhome.api.TutorInHomeService;
import com.tnb.f3r10.tutorinhome.domain.FirebaseAPI;
import com.tnb.f3r10.tutorinhome.entities.Tutor;
import com.tnb.f3r10.tutorinhome.lib.base.EventBus;
import com.tnb.f3r10.tutorinhome.tutorlist.events.TutorListEvent;

import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by f3r10 on 13/7/16.
 */
public class TutorListRepositoryImpl implements TutorListRepository {
    EventBus eventBus;
    TutorInHomeService service;
    FirebaseAPI firebaseAPI;

    public TutorListRepositoryImpl(EventBus eventBus,TutorInHomeService service, FirebaseAPI firebaseAPI) {
        this.eventBus = eventBus;
        this.service = service;
        this.firebaseAPI = firebaseAPI;
    }

    @Override
    public void getTutors() {
        Call<List<Tutor>> call = service.getTutors();
        call.enqueue(new Callback<List<Tutor>>() {
            @Override
            public void onResponse(Call<List<Tutor>> call, Response<List<Tutor>> response) {
                if(response.isSuccess()){
                    List<Tutor> tutorList = response.body();
                    if(tutorList.size() > 0 ){
                        post(tutorList);
                    }else{
                        post("Ups no hay tutores, intentaremos añadir mas tutores en el futuro", TutorListEvent.ERROR_EVENT);
                    }
                }else{
                    post("Ups, hubo un error en la respuesta", TutorListEvent.ERROR_EVENT);
                }
            }

            @Override
            public void onFailure(Call<List<Tutor>> call, Throwable t) {
                post(t.getLocalizedMessage(), TutorListEvent.ERROR_EVENT);
            }
        });

    }

    @Override
    public void signOff() {
        firebaseAPI.signOff();
    }

    private void post(String localizedMessage, int errorEvent) {
        TutorListEvent event = new TutorListEvent();
        event.setType(errorEvent);
        event.setError(localizedMessage);
        eventBus.post(event);
    }

    private void post(List<Tutor> tutors) {
        TutorListEvent event = new TutorListEvent();
        event.setType(TutorListEvent.READ_EVENT);
        event.setTutors(tutors);
        eventBus.post(event);
    }

}
