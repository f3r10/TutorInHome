package com.tnb.f3r10.tutorinhome.tutorlist;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.tnb.f3r10.tutorinhome.BaseTest;
import com.tnb.f3r10.tutorinhome.BuildConfig;
import com.tnb.f3r10.tutorinhome.R;
import com.tnb.f3r10.tutorinhome.domain.Util;
import com.tnb.f3r10.tutorinhome.entities.EducationTutor;
import com.tnb.f3r10.tutorinhome.entities.Ratings;
import com.tnb.f3r10.tutorinhome.entities.Tutor;
import com.tnb.f3r10.tutorinhome.lib.base.ImageLoader;
import com.tnb.f3r10.tutorinhome.login.ui.LoginActivity;
import com.tnb.f3r10.tutorinhome.support.ShadowRecyclerView;
import com.tnb.f3r10.tutorinhome.support.ShadowRecyclerViewAdapter;
import com.tnb.f3r10.tutorinhome.tutorlist.ui.TutorListActivity;
import com.tnb.f3r10.tutorinhome.tutorlist.ui.TutorListView;
import com.tnb.f3r10.tutorinhome.tutorlist.ui.adapter.OnItemClickListener;
import com.tnb.f3r10.tutorinhome.tutorlist.ui.adapter.TutorListAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.internal.ShadowExtractor;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.util.ActivityController;

import java.util.List;


import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;


/**
 * Created by f3r10 on 16/7/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23,
        shadows = {ShadowRecyclerView.class, ShadowRecyclerViewAdapter.class})
public class TutorListActivityTest extends BaseTest {
    @Mock
    Tutor tutor;
    @Mock
    private Ratings ratings;
    @Mock
    private EducationTutor educationTutor;
    @Mock
    private List<EducationTutor> educationTutorList;
    @Mock
    List<Tutor> tutorList;
    @Mock
    ImageLoader imageLoader;
    @Mock
    TutorListPresenter presenter;
    @Mock
    TutorListAdapter adapter;
    @Mock
    private Util util;

    private TutorListView view;
    private OnItemClickListener listener;
    private TutorListActivity activity;

    private ShadowActivity shadowActivity;
    private ActivityController<TutorListActivity> controller;

    private ShadowRecyclerViewAdapter shadowAdapter;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        TutorListActivity tutorListActivity = new TutorListActivity(){
            @Override
            public void setTheme(int resid) {
                super.setTheme(R.style.AppTheme_NoActionBar);
            }

            @Override
            public TutorListAdapter getAdapter(){
                return adapter;
            }

            @Override
            public  TutorListPresenter getPresenter(){
                return presenter;
            }
        };

        controller = ActivityController.of(Robolectric.getShadowsAdapter(), tutorListActivity).create().visible();
        activity = controller.get();
        view = (TutorListView)activity;
        listener = (OnItemClickListener)activity;

        shadowActivity = shadowOf(activity);
    }

    @Test
    public void testOnCreate_ShouldCallPresenter() throws Exception {
        verify(presenter).onCreate();
        verify(presenter).getTutors();
    }

    @Test
    public void testOnDestroy_ShouldCallPresenter() throws Exception {
        presenter.onDestroy();
        verify(presenter).onDestroy();
    }

    @Test
    public void testSetTutorsFromPresenter_SholudSetInAdapter() throws Exception {
        view.setTutors(tutorList);
        verify(adapter).setTutors(tutorList);
    }

    @Test
    public void logoutMenuClicked_launchLoginActivity() {
        shadowActivity.clickMenuItem(R.id.action_logout);
        Intent intent = shadowActivity.peekNextStartedActivity();
        assertEquals(new ComponentName(activity, LoginActivity.class), intent.getComponent());
    }

    @Test
    public void recyclerViewScrollToTop_OnToolbarClick() {
        int scrollPosition = 1;

        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.recyclerViewTutors);
        ShadowRecyclerView shadowRecyclerView = (ShadowRecyclerView) ShadowExtractor.extract(recyclerView);

        recyclerView.smoothScrollToPosition(scrollPosition);
        assertEquals(scrollPosition, shadowRecyclerView.getSmoothScrollToPosition());


    }


    private void setUpShadowAdapter(int pos){
        when(util.getAvatarUrl("")).thenReturn("http://www.tutorinhome.com");
        when(tutor.getRatings()).thenReturn(ratings);
        when(tutor.getEducationTutor()).thenReturn(educationTutorList);
        when(tutorList.get(pos)).thenReturn(tutor);

        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.recyclerViewTutors);
        TutorListAdapter adapter = new TutorListAdapter(tutorList, imageLoader, listener, util);
        recyclerView.setAdapter(adapter);
        shadowAdapter =(ShadowRecyclerViewAdapter)ShadowExtractor.extract(adapter);

    }
}
