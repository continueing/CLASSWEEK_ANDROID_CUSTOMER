package com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.review;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.domain.class_info.ClassSummaryInfo;
import com.blackpigstudio.classweek.main.domain.class_info.Review;
import com.blackpigstudio.classweek.main.module.etc.AppTerminator;
import com.blackpigstudio.classweek.main.module.listview.OnScrollOfListViewListener;
import com.blackpigstudio.classweek.main.module.listview.ProgressbarFooter;
import com.blackpigstudio.classweek.main.module.network.ClassRequest;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.network.JsonResponseHandler;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by continueing on 2014. 5. 22..
 */
public class ReviewDialog extends Dialog implements OnScrollOfListViewListener {
    public static final String SCREEN_NAME = "review";
    private ViewForReviewDialog view;
    private int companyId;
    private String companyName;
    private int nextPage = 1;
    private boolean isFinalPage = false;
    private boolean isRequestingHttpNow = false;
    private EasyTracker easyTracker;

    public ReviewDialog(Context context, int aCompanyId, String aCompanyName) {
        super(context);
        this.companyId = aCompanyId;
        this.companyName = aCompanyName;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = new ViewForReviewDialog(getContext(), this);
        setContentView(view.getRoot());
        view.setData(companyName);
        requestCompanyReviewsFromServer();
    }


    @Override
    public void atScrollIsOnEndItem() {
        if(!isRequestingHttpNow && !isFinalPage )
            requestCompanyReviewsFromServer();
    }

    private void requestCompanyReviewsFromServer()
    {
        isRequestingHttpNow = true;
        ClassRequest classRequest = new ClassRequest(getContext());
        try {
            classRequest.getCompanyReviews(companyId, nextPage, getCompanyReviewsListener);
        } catch (JSONException e) {
            AppTerminator.error(this, "classRequest.getCompanyReviews fail :" + e.toString());
        }
    }

    HttpRequester.NetworkResponseListener getCompanyReviewsListener = new HttpRequester.NetworkResponseListener() {
        @Override
        public void onSuccess(JSONObject jsonObject) {
            JSONArray jsonArray = null;

            try {
                jsonArray = jsonObject.getJSONArray(JsonResponseHandler.PARM_DATA);
            } catch (JSONException e) {
                AppTerminator.error(this, "JSONObject.getJSONArray(): " + e.toString());
            }

            ArrayList<ViewForReviewItem.IReviewItem> iReviewItems = new ArrayList<ViewForReviewItem.IReviewItem>();

            for(int i = 0 ; i < jsonArray.length() ; i++)
            {
                JSONObject jsonReviewObject = null;
                try {
                    jsonReviewObject = jsonArray.getJSONObject(i);
                } catch (JSONException e) {
                    AppTerminator.error(this, "jsonArray.getJSONObject: " + e.toString());
                }

                try {
                    iReviewItems.add(new Review(jsonReviewObject));
                } catch (JSONException e) {
                    AppTerminator.error(this, "Review.new: " + e.toString());
                }
            }

            view.addReviewItemArrayList(iReviewItems);
            isFinalPage = checkIsFinalPage(iReviewItems.size());
            isRequestingHttpNow = false;
        }

        private boolean checkIsFinalPage(int aSizeOfClassSummaryInfos)
        {
            if(aSizeOfClassSummaryInfos >= 10 ) // not yet
            {
                nextPage++;
                return false;
            }
            else // final
            {
                view.setProgressbarVisibility(false);
                return true;
            }
        }

        @Override
        public void onFail(JSONObject jsonObject, int errorCode) {
            if(errorCode == JsonResponseHandler.ERROR_CODE_NETWORK_UNAVAILABLE) {
                Toast.makeText(getContext(),getOwnerActivity().getResources().getString(R.string.network_check_alert), Toast.LENGTH_LONG).show();
                dismiss();
            }
            else
                AppTerminator.error(this, "classRequest.getCompanyReviews fail : " + errorCode);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        easyTracker = EasyTracker.getInstance(getOwnerActivity());
        easyTracker.set(Fields.SCREEN_NAME, SCREEN_NAME+"/"+companyId );
        easyTracker.send(MapBuilder
                        .createAppView()
                        .build()
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        easyTracker.activityStop(getOwnerActivity());
    }
}
