package com.tnb.f3r10.tutorinhome.tutorlist;

import com.tnb.f3r10.tutorinhome.BaseTest;
import com.tnb.f3r10.tutorinhome.api.TutorInHomeService;
import com.tnb.f3r10.tutorinhome.domain.FirebaseAPI;
import com.tnb.f3r10.tutorinhome.entities.Tutor;
import com.tnb.f3r10.tutorinhome.lib.base.EventBus;
import com.tnb.f3r10.tutorinhome.tutorlist.events.TutorListEvent;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by f3r10 on 16/7/16.
 */
public class TutorListRepositoryImplTest extends BaseTest {
    @Mock
    private EventBus eventBus;
    @Mock
    private TutorInHomeService service;
    @Mock
    private Tutor tutor;
    @Mock
    private FirebaseAPI firebaseAPI;

    private ArgumentCaptor<TutorListEvent> tutorListEventArgumentCaptor;
    private TutorListRepository repository;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        repository = new TutorListRepositoryImpl(eventBus, service, firebaseAPI);
        tutorListEventArgumentCaptor = ArgumentCaptor.forClass(TutorListEvent.class);
    }

    @Test
    public void testGetTutorsCalled_APIServiceSuccessCall_postedEvent() throws Exception {
        when(service.getTutors()).thenReturn(buildCall(true, null));

        repository.getTutors();

        verify(service).getTutors();
        verify(eventBus).post(tutorListEventArgumentCaptor.capture());
        TutorListEvent event = tutorListEventArgumentCaptor.getValue();
        assertEquals(TutorListEvent.READ_EVENT, event.getType());
        assertNull(event.getError());
        assertNotNull(event.getTutors());
        assertEquals(tutor, event.getTutors().get(0));
    }

    @Test
    public void testGetTutorsCalled_APIServiceErrorCall_postedEvent() throws Exception {
        String msgError = "error";
        when(service.getTutors()).thenReturn(buildCall(false, msgError));

        repository.getTutors();

        verify(service).getTutors();
        verify(eventBus).post(tutorListEventArgumentCaptor.capture());
        TutorListEvent event = tutorListEventArgumentCaptor.getValue();
        assertEquals(TutorListEvent.ERROR_EVENT, event.getType());
        assertNotNull(event.getError());
        assertNull(event.getTutors());
        assertEquals(msgError, event.getError());
    }


    private Call<List<Tutor>> buildCall(final boolean success, final String errorMgs){
        Call<List<Tutor>> response = new Call<List<Tutor>>() {
            @Override
            public Response<List<Tutor>> execute() throws IOException {
                Response<List<Tutor>> result = null;

                if(success){
                    List<Tutor> response = new ArrayList<Tutor>();
                    response.add(tutor);
                    result = Response.success(response);
                }else{
                    result = Response.error(null, null);
                }
                return result;
            }

            @Override
            public void enqueue(Callback<List<Tutor>> callback) {
                if(success){
                    try {
                        callback.onResponse(this, execute());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    callback.onFailure(this, new Throwable(errorMgs));
                }
            }

            @Override
            public boolean isExecuted() {
                return true;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<List<Tutor>> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };

        return response;

    }
}
