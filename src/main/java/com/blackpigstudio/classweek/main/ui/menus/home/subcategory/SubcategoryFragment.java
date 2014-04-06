package com.blackpigstudio.classweek.main.ui.menus.home.subcategory;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.homeui.AbstractHomeFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class SubcategoryFragment extends AbstractHomeFragment implements ViewForSubcategoryFragment.OnSubCategoryChooseListener{
    public static final String BUNDLE_PARM_OF_SPINNER_INDEX = "spinner_index";

    public SubcategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        setSpinnerItemIndex(bundle.getInt(BUNDLE_PARM_OF_SPINNER_INDEX));
        getActivity().setTitle(bundle.getInt(BUNDLE_PARM_OF_SPINNER_INDEX)+"");
        ViewForSubcategoryFragment viewForSubcategoryFragment = new ViewForSubcategoryFragment(getActivity().getApplicationContext(),inflater,container,this);
        return viewForSubcategoryFragment.getRoot();
    }


    @Override
    public void onSubCategoryChoose(int index) {

    }
}
