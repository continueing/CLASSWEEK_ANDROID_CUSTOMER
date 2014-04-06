package com.blackpigstudio.classweek.main.module.homeui;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.ui.MainActivity;
import com.blackpigstudio.classweek.main.module.subcategoryspinner.HomeSpinnerAdapter;
import com.blackpigstudio.classweek.main.ui.menus.home.dance.DanceSubcategoryFragment;
import com.blackpigstudio.classweek.main.ui.menus.home.filter.FilterActivity;
import com.blackpigstudio.classweek.main.ui.menus.home.music.MusicSubcategoryFragment;
import com.blackpigstudio.classweek.main.ui.menus.home.recommendation.ClassRecommendationFragment;
import com.blackpigstudio.classweek.main.ui.menus.home.subcategory.SubcategoryFragment;


abstract public class AbstractHomeFragment extends AbstractFilterActionFragment {
    public static final String BUNDLE_PARM_OF_URL = "url";

    private int spinnerItemIndexOfThisFragment;

    public AbstractHomeFragment() {
        spinnerItemIndexOfThisFragment = -1;
    }

    protected void setSpinnerItemIndex(int aIndex)
    {
        this.spinnerItemIndexOfThisFragment = aIndex;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        SpinnerAdapter spinnerAdapter = HomeSpinnerAdapter.getInstance(getActivity().getApplicationContext());
        actionBar.setListNavigationCallbacks(spinnerAdapter, new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int i, long l) {
                if(i != spinnerItemIndexOfThisFragment) {
                    switch (i) {
                        case 0:
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, ClassRecommendationFragment.getInstance()).commit();
                            break;
                        case 1:
                        case 2:
                            SubcategoryFragment subcategoryFragment= new SubcategoryFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt(SubcategoryFragment.BUNDLE_PARM_OF_SPINNER_INDEX,i);
                            subcategoryFragment.setArguments(bundle);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, subcategoryFragment).commit();
                            break;
                        default:
                            Log.e(this.getClass().getCanonicalName(), "OnNavigationListener.onNavigationItemSelected: unexpected spinner item index");
                            System.exit(-1);
                            break;
                    }

                }
                return false;
            }
        });
        actionBar.setSelectedNavigationItem(spinnerItemIndexOfThisFragment);
    }


}
