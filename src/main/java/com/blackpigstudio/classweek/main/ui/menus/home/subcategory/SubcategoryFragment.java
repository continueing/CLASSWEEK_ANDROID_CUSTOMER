package com.blackpigstudio.classweek.main.ui.menus.home.subcategory;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.Subcategory;
import com.blackpigstudio.classweek.main.module.homeui.AbstractHomeFragment;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.ui.MainActivity;
import com.blackpigstudio.classweek.main.ui.menus.home.class_detail_info.ClassDetailInfoActivity;
import com.blackpigstudio.classweek.main.ui.menus.home.class_summary_info_inventory.ClassSummeryInfoInventoryActivity;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class SubcategoryFragment extends AbstractHomeFragment implements ViewForSubcategoryFragment.OnSubCategoryChooseListener, HttpRequester.NetworkResponseListener{
    public static final String BUNDLE_PARM_OF_SPINNER_INDEX = "spinner_index";
    private ViewForSubcategoryFragment viewForSubcategoryFragment;


    public SubcategoryFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        setSpinnerItemIndex(bundle.getInt(BUNDLE_PARM_OF_SPINNER_INDEX));
        getActivity().setTitle(bundle.getInt(BUNDLE_PARM_OF_SPINNER_INDEX)+"");
        viewForSubcategoryFragment = new ViewForSubcategoryFragment(getActivity().getApplicationContext(),inflater,container,this);
        requestClassSummaryInfoFromServer(" classweek/" );
        return viewForSubcategoryFragment.getRoot();
    }





    private void requestClassSummaryInfoFromServer(String url)
    {
        HttpRequester.foo(this, url);
    }


    @Override
    public void onSubCategoryChoose(int index) {
        Intent intent = new Intent(getActivity().getApplicationContext(), ClassSummeryInfoInventoryActivity.class);
        intent.putExtra(ClassSummeryInfoInventoryActivity.BUNDLE_PARM_OF_TITLE,""+index);
        intent.putExtra(ClassSummeryInfoInventoryActivity.BUNDLE_PARM_OF_URL_KEY,""+index);
        startActivity(intent);
    }




    /*
        temporal handler for dummy ClassSummaryInfos
     */
    Handler tmp = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            ArrayList<ViewForSubcategoryListViewItem.ISubcategory> subcategories = new ArrayList<ViewForSubcategoryListViewItem.ISubcategory>();
            for(int i = 1; i < 80; i++ )
                subcategories.add(new Subcategory());
            viewForSubcategoryFragment.addISubcategoryArrayList(subcategories);
        }
    };

    @Override
    public void onSuccess(JSONObject jsonObject) {
        tmp.sendEmptyMessage(0);
    }

    @Override
    public void onFail(JSONObject jsonObject, int errorCode) {

    }
}
