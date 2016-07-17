package com.tnb.f3r10.tutorinhome.tutorlist;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tnb.f3r10.tutorinhome.BaseTest;
import com.tnb.f3r10.tutorinhome.BuildConfig;
import com.tnb.f3r10.tutorinhome.R;
import com.tnb.f3r10.tutorinhome.domain.Util;
import com.tnb.f3r10.tutorinhome.entities.EducationTutor;
import com.tnb.f3r10.tutorinhome.entities.Ratings;
import com.tnb.f3r10.tutorinhome.entities.Tutor;
import com.tnb.f3r10.tutorinhome.lib.base.ImageLoader;
import com.tnb.f3r10.tutorinhome.support.ShadowRecyclerViewAdapter;
import com.tnb.f3r10.tutorinhome.tutorlist.ui.adapter.OnItemClickListener;
import com.tnb.f3r10.tutorinhome.tutorlist.ui.adapter.TutorListAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.internal.ShadowExtractor;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by f3r10 on 16/7/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21,
        shadows = {ShadowRecyclerViewAdapter.class})
public class TutorListAdapterTest extends BaseTest {
    @Mock
    private Tutor tutor;
    @Mock
    private Ratings ratings;
    @Mock
    private EducationTutor educationTutor;
    @Mock
    private List<EducationTutor> educationTutorList;
    @Mock
    private List<Tutor> tutorList;
    @Mock
    private ImageLoader imageLoader;
    @Mock
    private OnItemClickListener onItemClickListener;
    @Mock
    private Util util;

    private TutorListAdapter adapter;
    private ShadowRecyclerViewAdapter shadowAdapter;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        when(util.getAvatarUrl("")).thenReturn("http://www.tutorinhome.com");
        when(tutor.getRatings()).thenReturn(ratings);
        when(tutor.getEducationTutor()).thenReturn(educationTutorList);
        adapter = new TutorListAdapter(tutorList, imageLoader, onItemClickListener, util);
        shadowAdapter =(ShadowRecyclerViewAdapter)ShadowExtractor.extract(adapter);

        Activity activity = Robolectric.setupActivity(Activity.class);
        RecyclerView recyclerView = new RecyclerView(activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);

    }

    @Test
    public void testSetTutors_ItemCountMatches() throws Exception {
        int itemCount = 5;
        when(tutorList.size()).thenReturn(itemCount);
        adapter.setTutors(tutorList);

        assertEquals(itemCount, adapter.getItemCount());

    }

    @Test
    public void testOnItemClick_ShouldCallListener() throws Exception {
        int positionToClick = 0;
        when(tutorList.get(positionToClick)).thenReturn(tutor);

        shadowAdapter.itemVisible(positionToClick);
        shadowAdapter.performItemClick(positionToClick);

        verify(onItemClickListener).onItemClick(tutor);

    }

    @Test
    public void testViewHolder_ShouldRenderNameTutor() throws Exception {
        int positionToShow = 0;
        String tutorName = "name";
        when(tutor.getNickName()).thenReturn(tutorName);
        when(tutorList.get(positionToShow)).thenReturn(tutor);

        shadowAdapter.itemVisible(positionToShow);

        View view = shadowAdapter.getViewHolderForPosition(positionToShow);
        TextView txtName = (TextView)view.findViewById(R.id.editTxtNameTutor);

        assertEquals(tutorName, txtName.getText().toString());

    }
}
