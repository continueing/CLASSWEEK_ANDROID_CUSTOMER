package com.blackpigstudio.classweek.main.ui.menus.home.music;



import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackpigstudio.classweek.main.domain.ClassSummaryInfo;
import com.blackpigstudio.classweek.main.module.homeui.AbstractHomeFragment;
import com.blackpigstudio.classweek.main.ui.menus.home.class_summary_info_inventory.ClassSummeryInfoInventoryActivity;

import java.util.ArrayList;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class MusicSubcategoryFragment extends AbstractHomeFragment implements ViewForMusicSubcategoryFragment.OnSubCategoryChooseListener {
    public static final int SPINNER_ITEM_INDEX = 1;
    private static MusicSubcategoryFragment instance;

    public MusicSubcategoryFragment() {
        super(SPINNER_ITEM_INDEX);
    }

    public static MusicSubcategoryFragment getInstance()
    {
        if(instance == null)
            instance = new MusicSubcategoryFragment();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewForMusicSubcategoryFragment viewForMusicSubcategoryFragment = new ViewForMusicSubcategoryFragment(getActivity(),inflater,container, this);

        return viewForMusicSubcategoryFragment.getRoot();
    }


    @Override
    public void onSubCategoryChoose(int index) {
        Intent intent = new Intent(getActivity(), ClassSummeryInfoInventoryActivity.class);
        startActivity(intent);
    }
}
