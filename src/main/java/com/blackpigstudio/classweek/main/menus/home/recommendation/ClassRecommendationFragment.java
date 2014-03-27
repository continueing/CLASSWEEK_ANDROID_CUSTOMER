package com.blackpigstudio.classweek.main.menus.home.recommendation;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;

import com.blackpigstudio.classweek.main.MainActivity;
import com.blackpigstudio.classweek.main.menus.home.spinner.HomeSpinnerAdapter;
import com.blackpigstudio.classweek.main.menus.home.spinner.OnHomeSpinnerNavigationListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class ClassRecommendationFragment extends Fragment {


    public ClassRecommendationFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        ClassRecommendationView classRecommendationView = new ClassRecommendationView(getActivity().getApplicationContext(),container);
        return classRecommendationView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        ActionBar actionBar  = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        SpinnerAdapter spinnerAdapter = new HomeSpinnerAdapter(getActivity().getApplicationContext());
        actionBar.setListNavigationCallbacks(spinnerAdapter,new OnHomeSpinnerNavigationListener());

    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
