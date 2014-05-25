package com.blackpigstudio.classweek.main.ui.menu.home.recommendation;



import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.class_info.ClassSummaryInfo;
import com.blackpigstudio.classweek.main.module.etc.AppTerminator;
import com.blackpigstudio.classweek.main.module.activity_and_fragment.homeui.AbstractHomeFragment;
import com.blackpigstudio.classweek.main.module.etc.EventOfGoogleAnalytics;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.IClassSummaryInfoItem;
import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.OnClassSummaryInfoChooseListener;
import com.blackpigstudio.classweek.main.module.network.ClassRequest;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.network.JsonResponseHandler;
import com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.ClassDetailInfoActivity;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class ClassRecommendationFragment extends AbstractHomeFragment implements OnClassSummaryInfoChooseListener {
    public static final String SCREEN_NAME = "recommendation";
    public static final int SPINNER_ITEM_INDEX = 0;
    private ViewForClassRecommendationFragment view;
    ArrayList<IClassSummaryInfoItem> iClassSummaryInfoItems = null;
    ArrayList<String> imageUrls = null;
    private EasyTracker easyTracker;

    public ClassRecommendationFragment() {
        setSpinnerItemIndex(SPINNER_ITEM_INDEX);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = new ViewForClassRecommendationFragment( getActivity(), inflater,container, this);
        View result = view.getRoot();
        requestRecommendedSubcategoryFromServer();
        getActivity().getActionBar().setTitle(R.string.title_section1);
        return result;
    }

    @Override
    public void onResume() {
        super.onResume();
        showPromotionToast();
    }

    private void requestRecommendedClassSummaryInfoFromServer() {
        ClassRequest classRequest = new ClassRequest(getActivity());
        try {
            classRequest.getRecommendedClassSummaryInfos(getRecommendedClassSummaryInfoListener);
        } catch (JSONException e) {
            AppTerminator.error(this, "classRequest.getRecommendedClassSummaryInfos fail :"+ e.toString());
        }
    }

    private void requestRecommendedSubcategoryFromServer() {
        ClassRequest classRequest = new ClassRequest(getActivity());
        try {
            classRequest.getRecommendedSubcategories(getRecommendedSubcategoriesListener);
        } catch (JSONException e) {
            AppTerminator.error(this, "classRequest.getRecommendedSubcategoriesListener fail :"+ e.toString());
        }
    }



    HttpRequester.NetworkResponseListener getRecommendedClassSummaryInfoListener = new HttpRequester.NetworkResponseListener() {
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
            view.setData(iClassSummaryInfoItems, imageUrls);

        }

        @Override
        public void onFail(JSONObject jsonObject, int errorCode) {
            if(errorCode == JsonResponseHandler.ERROR_CODE_NETWORK_UNAVAILABLE) {
                AppTerminator.finishActivityWithToast(getResources().getString(R.string.network_check_alert),getActivity());
            }
            else
                AppTerminator.error(this, "classRequest.getRecommendedClassSummaryInfos fail : " + errorCode);
        }
    };


    HttpRequester.NetworkResponseListener getRecommendedSubcategoriesListener = new HttpRequester.NetworkResponseListener() {
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
            requestRecommendedClassSummaryInfoFromServer();
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
    public void onStart() {
        super.onStart();
        easyTracker = EasyTracker.getInstance(getActivity());
        easyTracker.set(Fields.SCREEN_NAME, SCREEN_NAME);
        easyTracker.send(MapBuilder
                        .createAppView()
                        .build()
        );
    }

    @Override
    public void onStop() {
        super.onStop();
        easyTracker.activityStop(getActivity());
    }

    @Override
    public void onClassSummaryInfoChoose(IClassSummaryInfoItem iClassSummaryInfoItem) {
        easyTracker.send(
                MapBuilder.createEvent(
                EventOfGoogleAnalytics.CATEGORY_VIEW,
                EventOfGoogleAnalytics.ACTION_CLASS,
                "recommendation",
                (long)iClassSummaryInfoItem.getClassId()
                ).build()
        );
        Intent intent = new Intent(getActivity(),ClassDetailInfoActivity.class);
        intent.putExtra(ClassDetailInfoActivity.BUNDLE_PARM_CLASS_ID, iClassSummaryInfoItem.getClassId());
        intent.putExtra(ClassDetailInfoActivity.BUNDLE_PARM_SCHEDULE_ID, iClassSummaryInfoItem.getScheduleId());
        startActivity(intent);
    }

    private void showPromotionToast()
    {
        View layout = LayoutInflater.from(getActivity()).inflate(R.layout.toast_promotion,null);
        Toast toast = new Toast(getActivity());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }



}
