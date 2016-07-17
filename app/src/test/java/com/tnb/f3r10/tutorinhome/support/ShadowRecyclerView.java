package com.tnb.f3r10.tutorinhome.support;

import android.support.v7.widget.RecyclerView;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.shadows.ShadowViewGroup;

/**
 * Created by f3r10 on 17/7/16.
 */
@Implements(value = RecyclerView.class)
public class ShadowRecyclerView extends ShadowViewGroup {
    private int smoothScrollPosition = -1;

    @Implementation
    public void smoothScrollToPosition(int position) {
        setSmoothScrollToPosition(position);
    }

    public int getSmoothScrollToPosition() {
        return smoothScrollPosition;
    }

    public void setSmoothScrollToPosition(int position) {
        smoothScrollPosition = position;
    }
}
