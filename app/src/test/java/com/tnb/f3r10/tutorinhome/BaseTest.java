package com.tnb.f3r10.tutorinhome;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowApplication;

/**
 * Created by f3r10 on 16/7/16.
 */
public abstract class BaseTest {
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        if(RuntimeEnvironment.application != null){
            ShadowApplication shadowApp = Shadows.shadowOf(RuntimeEnvironment.application);
            shadowApp.grantPermissions("android.permission.INTERNET");
        }

    }
}
