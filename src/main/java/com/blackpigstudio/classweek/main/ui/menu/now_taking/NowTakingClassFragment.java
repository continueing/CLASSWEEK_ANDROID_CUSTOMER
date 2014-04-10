package com.blackpigstudio.classweek.main.ui.menu.now_taking;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class NowTakingClassFragment extends Fragment {


    public NowTakingClassFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewForNowTakingClassesFragment viewForNowTakingClassesFragment = new ViewForNowTakingClassesFragment(getActivity().getApplicationContext(),inflater,container);
        return  viewForNowTakingClassesFragment.getRoot();
    }

}
