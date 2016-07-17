package com.tnb.f3r10.tutorinhome.tutorlist;

import com.tnb.f3r10.tutorinhome.BaseTest;

import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

/**
 * Created by f3r10 on 16/7/16.
 */
public class TutorListInteractorImplTest extends BaseTest {
    @Mock
    TutorListRepository repository;
    
    private TutorListInteractor interactor;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        
        interactor = new TutorListInteractorImpl(repository);
    }

    @Test
    public void testExecute_callRepository() throws Exception {
        interactor.execute();
        verify(repository).getTutors();

    }
}
