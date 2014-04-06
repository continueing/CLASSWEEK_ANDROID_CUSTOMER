package com.blackpigstudio.classweek.main.ui.menus.home.dance;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackpigstudio.classweek.main.module.homeui.AbstractHomeFragment;

public class DanceSubcategoryFragment extends AbstractHomeFragment {
    public static final int SPINNER_ITEM_INDEX = 2;
    private static DanceSubcategoryFragment instance;

    public DanceSubcategoryFragment() {
    }

    public static DanceSubcategoryFragment getInstance()
    {
        if(instance == null)
            instance = new DanceSubcategoryFragment();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewForDanceSubcategoryFragment subcategoryFragmentView = new ViewForDanceSubcategoryFragment(getActivity(), inflater, container);
        Bundle bundle = getArguments();
        return subcategoryFragmentView.getRoot();
    }
}
