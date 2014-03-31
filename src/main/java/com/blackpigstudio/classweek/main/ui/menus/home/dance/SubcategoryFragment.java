package com.blackpigstudio.classweek.main.ui.menus.home.dance;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackpigstudio.classweek.main.module.AbstractHomeFragment;

public class SubcategoryFragment extends AbstractHomeFragment {
    public static final int SPINNER_ITEM_INDEX = 1;
    private static SubcategoryFragment instance;
    private SubcategoryFragment() {
        super(SPINNER_ITEM_INDEX);
    }

    public static SubcategoryFragment getInstance()
    {
        if(instance == null)
            instance = new SubcategoryFragment();
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SubcategoryFragmentView subcategoryFragmentLayout = new SubcategoryFragmentView(getActivity(), inflater, container);
        return subcategoryFragmentLayout.getRoot();
    }

}
