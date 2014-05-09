package com.blackpigstudio.classweek.main.ui.menu.home.recommendation;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.class_info.ClassSummaryInfo;
import com.blackpigstudio.classweek.main.module.AppTerminator;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.homeui.AbstractHomeFragment;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.IClassSummaryInfoItem;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.OnClassSummeryInfoChooseListener;
import com.blackpigstudio.classweek.main.module.network.ClassRequest;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.network.JsonResponseHandler;
import com.blackpigstudio.classweek.main.ui.MainActivity;
import com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.ClassDetailInfoActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class ClassRecommendationFragment extends AbstractHomeFragment implements OnClassSummeryInfoChooseListener, HttpRequester.NetworkResponseListener{
    public static final int SPINNER_ITEM_INDEX = 0;
    private static ClassRecommendationFragment instance;
    private ViewForClassRecommendationFragment view;
    ArrayList<IClassSummaryInfoItem> iClassSummaryInfoItems = null;
    ArrayList<String> imageUrls = null;

    public ClassRecommendationFragment() {
        setSpinnerItemIndex(SPINNER_ITEM_INDEX);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = new ViewForClassRecommendationFragment( getActivity(), inflater,container, this);
        View result = view.getRoot();
        requestRecommendedClassSummaryInfoFromServer();
        requestRecommendedSubcategoryFromServer();
        getActivity().getActionBar().setTitle(R.string.title_section1);
        return result;
    }

    private void requestRecommendedClassSummaryInfoFromServer() {
        ClassRequest classRequest = new ClassRequest(getActivity());
        try {
            classRequest.getRecommendedClassSummaryInfos(this);
        } catch (JSONException e) {
            AppTerminator.error(this, "classRequest.getRecommendedClassSummaryInfos fail :"+ e.toString());
        }
    }

    private void requestRecommendedSubcategoryFromServer() {
        ClassRequest classRequest = new ClassRequest(getActivity());
        try {
            classRequest.getRecommendedSubcategories(getRecommendedSubcategories);
        } catch (JSONException e) {
            AppTerminator.error(this, "classRequest.getRecommendedSubcategories fail :"+ e.toString());
        }
    }




    HttpRequester.NetworkResponseListener getRecommendedSubcategories = new HttpRequester.NetworkResponseListener() {
        @Override
        public void onSuccess(JSONObject jsonObject) {

            JSONArray jsonArray = null;

            try {
                jsonArray = jsonObject.getJSONArray(JsonResponseHandler.PARM_DATA);
            } catch (JSONException e) {
                AppTerminator.error(this, "JSONObject.getJSONArray(): " + e.toString());
            }

            imageUrls = new ArrayList<String>();

            for(int i = 0 ; i < jsonArray.length() ; i++)
            {
                JSONObject jsonSubcategoryObject = null;
                try {
                    jsonSubcategoryObject = jsonArray.getJSONObject(i);
                } catch (JSONException e) {
                    AppTerminator.error(this, "jsonArray.getJSONObject: " + e.toString());
                }

                try {
                    imageUrls.add(jsonSubcategoryObject.getString("image_url"));
                } catch (JSONException e) {
                    AppTerminator.error(this, "jsonSubcategoryObject.getString(\"image_url\"): " + e.toString());
                }
            }
            setDataIfPossible();
        }

        @Override
        public void onFail(JSONObject jsonObject, int errorCode) {
            if(errorCode == JsonResponseHandler.ERROR_CODE_NETWORK_UNAVAILABLE) {
                AppTerminator.finishActivityWithToast(getResources().getString(R.string.network_check_alert),getActivity());
            }
            else
                AppTerminator.error(this, "classRequest.getRecommendedSubcategories fail : " + errorCode);
        }
    };




    @Override
    public void onClassSummeryInfoChoose(IClassSummaryInfoItem iClassSummaryInfoItem) {
        Intent intent = new Intent(getActivity(),ClassDetailInfoActivity.class);
        intent.putExtra(ClassDetailInfoActivity.BUNDLE_PARM_CLASS_ID, iClassSummaryInfoItem.getClassId());
        intent.putExtra(ClassDetailInfoActivity.BUNDLE_PARM_SCHEDULE_ID, iClassSummaryInfoItem.getScheduleId());
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

        iClassSummaryInfoItems = new ArrayList<IClassSummaryInfoItem>();

        for(int i = 0 ; i < jsonArray.length() ; i++)
        {
            JSONObject jsonClassSummaryInfoObject = null;
            try {
                jsonClassSummaryInfoObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                AppTerminator.error(this, "jsonArray.getJSONObject: " + e.toString());
            }

            try {
                iClassSummaryInfoItems.add(new ClassSummaryInfo(jsonClassSummaryInfoObject));
            } catch (JSONException e) {
                AppTerminator.error(this, "ClassSummaryInfo.new: " + e.toString());
            }
        }
        setDataIfPossible();
    }

    @Override
    public void onFail(JSONObject jsonObject, int errorCode) {
        if(errorCode == JsonResponseHandler.ERROR_CODE_NETWORK_UNAVAILABLE) {
            AppTerminator.finishActivityWithToast(getResources().getString(R.string.network_check_alert),getActivity());
        }
        else
            AppTerminator.error(this, "classRequest.getRecommendedClassSummaryInfos fail : " + errorCode);
    }

    private void setDataIfPossible()
    {
        if(imageUrls != null && iClassSummaryInfoItems != null)
            view.setData(iClassSummaryInfoItems, imageUrls);
    }


}
