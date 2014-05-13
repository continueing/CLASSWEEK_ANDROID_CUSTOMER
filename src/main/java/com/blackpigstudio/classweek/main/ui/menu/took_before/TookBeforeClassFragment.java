package com.blackpigstudio.classweek.main.ui.menu.took_before;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackpigstudio.classweek.R;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class TookBeforeClassFragment extends Fragment {
    public static final String SCREEN_NAME = "tookBefore";

    public TookBeforeClassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getActionBar().setTitle("종료된 클래스");
        ViewForTookBeforeClassFragment viewForTookBeforeClassFragment = new ViewForTookBeforeClassFragment(getActivity().getApplicationContext(),inflater,container);
        return viewForTookBeforeClassFragment.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        Tracker easyTracker = EasyTracker.getInstance(getActivity());
        easyTracker.set(Fields.SCREEN_NAME, SCREEN_NAME);
        easyTracker.send(MapBuilder
                        .createAppView()
                        .build()
        );
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(getActivity()).activityStop(getActivity());
    }

}
