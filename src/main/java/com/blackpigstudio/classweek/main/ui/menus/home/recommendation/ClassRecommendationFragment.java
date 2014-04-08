package com.blackpigstudio.classweek.main.ui.menus.home.recommendation;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackpigstudio.classweek.main.module.activity_and_fragment.homeui.AbstractHomeFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class ClassRecommendationFragment extends AbstractHomeFragment  {
    public static final int SPINNER_ITEM_INDEX = 0;
    private static ClassRecommendationFragment instance;

    public ClassRecommendationFragment() {
        setSpinnerItemIndex(SPINNER_ITEM_INDEX);
    }

    public static ClassRecommendationFragment getInstance()
    {
        if(instance == null)
            instance = new ClassRecommendationFragment();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewForClassRecommendationFragment classRecommendationFragmentView = new ViewForClassRecommendationFragment( getActivity(), inflater,container);
        return classRecommendationFragmentView.getRoot();
    }



}
