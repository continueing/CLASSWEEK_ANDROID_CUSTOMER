package com.blackpigstudio.classweek.main.ui.menu.home.subcategory;



import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.Subcategory;
import com.blackpigstudio.classweek.main.module.AppTerminator;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.homeui.AbstractHomeFragment;
import com.blackpigstudio.classweek.main.module.network.ClassRequest;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.network.JsonResponseHandler;
import com.blackpigstudio.classweek.main.ui.menu.home.class_summary_info_inventory.ClassSummaryInfoInventoryActivity;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class SubcategoryFragment extends AbstractHomeFragment implements ViewForSubcategoryFragment.OnSubCategoryChooseListener, HttpRequester.NetworkResponseListener{
    public static final String SCREEN_NAME = "subcategory";
    public static final String BUNDLE_PARM_OF_SPINNER_INDEX = "SPINNER_INDEX";
    public static final String BUNDLE_PARM_OF_CATEGORY_NAME = "CATEGORY_NAME";
    private ViewForSubcategoryFragment view;
    private String category;


    public SubcategoryFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        setSpinnerItemIndex(bundle.getInt(BUNDLE_PARM_OF_SPINNER_INDEX));
        category = bundle.getString(BUNDLE_PARM_OF_CATEGORY_NAME);
        view = new ViewForSubcategoryFragment(getActivity().getApplicationContext(),inflater,container,this);
        requestClassSummaryInfoFromServer(category);
        return view.getRoot();
    }





    private void requestClassSummaryInfoFromServer(String aCategory)
    {
        ClassRequest classRequest = new ClassRequest(getActivity());
        try {
            classRequest.getSubcategories(aCategory,this);
        } catch (JSONException e) {
            AppTerminator.error(this,"ClassRequest.getSubcategories(): " + e.toString());
        }
    }


    @Override
    public void onSubCategoryChoose(String aSubcategoryForUrl, String aSubcategoryForKor) {
        Intent intent = new Intent(getActivity().getApplicationContext(), ClassSummaryInfoInventoryActivity.class);
        intent.putExtra(ClassSummaryInfoInventoryActivity.BUNDLE_PARM_OF_SUBCATEGORY_FOR_URL, aSubcategoryForUrl);
        intent.putExtra(ClassSummaryInfoInventoryActivity.BUNDLE_PARM_OF_CATEGORY_URL, category );
        intent.putExtra(ClassSummaryInfoInventoryActivity.BUNDLE_PARM_OF_SUBCATEGORY_KOR, aSubcategoryForKor);
        startActivity(intent);
    }


    @Override
    public void onSuccess(JSONObject jsonObject) {
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray(JsonResponseHandler.PARM_DATA);
        } catch (JSONException e) {
            AppTerminator.error(this, "JSONObject.getJSONArray(): " + e.toString());
        }

        ArrayList<ViewForSubcategoryListViewItem.ISubcategory> iSubcategories = new ArrayList<ViewForSubcategoryListViewItem.ISubcategory>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = null;
            try {
                object = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                AppTerminator.error(this, "jsonArray.getJSONObject: " + e.toString());
            }

            try {
                iSubcategories.add(new Subcategory(object));
            } catch (JSONException e) {
                AppTerminator.error(this, "Subcategory.new: " + e.toString());
            }
        }

        view.addISubcategoryArrayList(iSubcategories);
        view.setProgressbarVisibility(false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Tracker easyTracker = EasyTracker.getInstance(getActivity());
        easyTracker.set(Fields.SCREEN_NAME,SCREEN_NAME+"/"+category);
        easyTracker.send(MapBuilder
                        .createAppView()
                        .build()
        );
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(getActivity()).activityStop(getActivity());
    }

    @Override
    public void onFail(JSONObject jsonObject, int errorCode) {
        if(errorCode == JsonResponseHandler.ERROR_CODE_NETWORK_UNAVAILABLE) {
            AppTerminator.finishActivityWithToast(getResources().getString(R.string.network_check_alert), getActivity());
        }
        else
            AppTerminator.error(this, "classRequest.getSubcategories fail : " + errorCode);
    }
}
