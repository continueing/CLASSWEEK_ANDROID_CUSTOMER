package com.blackpigstudio.classweek.main.ui.etc.exit_survey_dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.blackpigstudio.classweek.R;
import com.blackpigstudio.classweek.main.module.etc.AppTerminator;
import com.blackpigstudio.classweek.main.module.network.HttpRequester;
import com.blackpigstudio.classweek.main.module.network.JsonResponseHandler;
import com.blackpigstudio.classweek.main.module.network.SurveyRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by continueing on 2014. 6. 2..
 */
public class ExitSurveyDialog extends Dialog implements ViewForExitSurveyDialog.Controller{
    private IDismissListener iDismissListener;
    ViewForExitSurveyDialog view;

    public ExitSurveyDialog(Context context, IDismissListener anIDismissListener ) {
        super(context);
        iDismissListener = anIDismissListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = new ViewForExitSurveyDialog(getContext(), this);
        setContentView(view.getRoot());
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getContext(),"번거로우시겠지만 설문 참여 꼭 부탁드립니다 ㅜㅜ",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSurveyRequested(int aChoice) {
        SurveyRequest surveyRequest = new SurveyRequest(getContext());
        try {
            surveyRequest.sendOpinion(aChoice, sendOpinionRequestListener);
        } catch (JSONException e) {
            AppTerminator.error(this, "surveyRequest.sendOpinion fail :" + e.toString());
        }

    }

    HttpRequester.NetworkResponseListener sendOpinionRequestListener = new HttpRequester.NetworkResponseListener() {
        @Override
        public void onSuccess(JSONObject jsonObject) {
            dismiss();
            iDismissListener.onDismissed();
        }

        @Override
        public void onFail(JSONObject jsonObject, int errorCode) {
            dismiss();
            iDismissListener.onDismissed();
        }
    };

    public static interface IDismissListener
    {
        public void onDismissed();
    }
}
