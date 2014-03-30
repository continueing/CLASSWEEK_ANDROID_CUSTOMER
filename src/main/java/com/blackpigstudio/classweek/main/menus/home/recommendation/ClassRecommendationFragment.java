package com.blackpigstudio.classweek.main.menus.home.recommendation;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackpigstudio.classweek.main.menus.home.module.AbstractHomeFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class ClassRecommendationFragment extends AbstractHomeFragment {
    public static final int SPINNER_ITEM_INDEX = 0;

    public ClassRecommendationFragment() {
        super(SPINNER_ITEM_INDEX);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ClassRecommendationFragmentLayout classRecommendationFragmentLayout = new ClassRecommendationFragmentLayout(getActivity().getApplicationContext(),container);
        return classRecommendationFragmentLayout;
    }

}
