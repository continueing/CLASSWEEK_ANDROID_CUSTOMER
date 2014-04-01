package com.blackpigstudio.classweek.main.ui.menus.home.music;



import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.homeui.AbstractHomeFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class MusicSubcategoryFragment extends AbstractHomeFragment implements ViewForMusicSubcategoryFragment.OnSubCategoryChooseListener {
    public static final int SPINNER_ITEM_INDEX = 1;
    private static MusicSubcategoryFragment instance;

    private MusicSubcategoryFragment() {
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
        // Change Fragment
    }
}
