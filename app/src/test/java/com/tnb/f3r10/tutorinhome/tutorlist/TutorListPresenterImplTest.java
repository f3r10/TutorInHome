package com.tnb.f3r10.tutorinhome.tutorlist;

import com.tnb.f3r10.tutorinhome.BaseTest;
import com.tnb.f3r10.tutorinhome.entities.Tutor;
import com.tnb.f3r10.tutorinhome.lib.base.EventBus;
import com.tnb.f3r10.tutorinhome.tutorlist.events.TutorListEvent;
import com.tnb.f3r10.tutorinhome.tutorlist.ui.TutorListView;

import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by f3r10 on 16/7/16.
 */
public class TutorListPresenterImplTest extends BaseTest {
    @Mock
    private EventBus eventBus;
    @Mock
    private TutorListView view;
    @Mock
    private TutorListInteractor interactor;
    @Mock
    private TutorListSessionInteractor sessionInteractor;
    @Mock
    private TutorListEvent event;
    @Mock
    private List<Tutor> tutors;

    private TutorListPresenterImpl presenter;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        presenter = new TutorListPresenterImpl(eventBus, view, interactor,sessionInteractor);
    }

    @Test
    public void testOnCreate_subscribedToEventBus() throws Exception {
        presenter.onCreate();
        verify(eventBus).register(presenter);
    }

    @Test
    public void testOnDestroy_UnsubscribedToEventBus() throws Exception {
        presenter.onDestroy();
        verify(eventBus).unregister(presenter);
        assertNull(presenter.getView());
    }

    @Test
    public void testGetTutors_executeGetTutorsInteractor() throws Exception {
        presenter.getTutors();
        assertNotNull(presenter.getView());
        verify(view).showProgress();
        verify(interactor).execute();
    }

    @Test
    public void testOnEvent_hasError() throws Exception {
        String msgError = "error";
        when(event.getError()).thenReturn(msgError);

        presenter.onEventMainThread(event);

        assertNotNull(presenter.getView());
        verify(view).hideProgress();

    }

    @Test
    public void testOnEvent_hasTutors() throws Exception {
        when(event.getType()).thenReturn(TutorListEvent.READ_EVENT);
        when(event.getTutors()).thenReturn(tutors);

        presenter.onEventMainThread(event);

        assertNotNull(presenter.getView());
        verify(view).hideProgress();
        verify(view).setTutors(event.getTutors());

    }

    @Test
    public void testGetView_returnsView() throws Exception {
        assertEquals(view, presenter.getView());

    }
}
