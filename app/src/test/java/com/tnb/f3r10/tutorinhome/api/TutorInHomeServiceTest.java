package com.tnb.f3r10.tutorinhome.api;

import com.tnb.f3r10.tutorinhome.BaseTest;
import com.tnb.f3r10.tutorinhome.BuildConfig;
import com.tnb.f3r10.tutorinhome.entities.Tutor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by f3r10 on 16/7/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class TutorInHomeServiceTest extends BaseTest {
    private TutorInHomeService service;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        TutorInHomeClient client = new TutorInHomeClient();
        service = client.getTutorInHomeService();
    }

    @Test
    public void doGetTutors_getTutorsFromBackend() throws Exception {
        Call<List<Tutor>> call = service.getTutors();

        Response<List<Tutor>> response = call.execute();
        assertTrue(response.isSuccess());

        List<Tutor> tutors = response.body();
        assertTrue(tutors.size() > 0);

        Tutor tutor = tutors.get(0);
        assertNotNull(tutor);

    }
}
