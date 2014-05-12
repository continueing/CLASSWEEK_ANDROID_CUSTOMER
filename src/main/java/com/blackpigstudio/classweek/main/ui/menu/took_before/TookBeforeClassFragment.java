package com.blackpigstudio.classweek.main.ui.menu.took_before;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackpigstudio.classweek.R;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class TookBeforeClassFragment extends Fragment {


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


}
