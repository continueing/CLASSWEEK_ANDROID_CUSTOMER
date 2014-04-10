package com.blackpigstudio.classweek.main.ui.nowtaking;



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
public class NowTakingClassesFragment extends Fragment {


    public NowTakingClassesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewForNowTakingClassesFragment viewForNowTakingClassesFragment = new ViewForNowTakingClassesFragment(getActivity().getApplicationContext(),inflater,container);
        return  viewForNowTakingClassesFragment.getRoot();
    }

}
