package com.blackpigstudio.classweek.main.module.network;

import android.content.Context;

import com.loopj.android.http.RequestParams;

import org.json.JSONException;

/**
 * Created by continueing on 2014. 6. 2..
 */
public class SurveyRequest {
    private Context context;

    private static String URL_BASE = "/foradmin/survey";
    private static String URL_OPINION =  "/opinion";

    private static final String PARM_KEY_OPINION_CODE = "opinion_code";

    public SurveyRequest(Context aContext)
    {
        context = aContext;
    }

    public void sendOpinion(int aChoice, final HttpRequester.NetworkResponseListener aNetworkResponseListener) throws JSONException
    {
        RequestParams requestParams = new RequestParams();
        requestParams.put(PARM_KEY_OPINION_CODE, ""+aChoice);// if Integer is in the parameter, the request sender does not recognize integer parameter
        HttpRequester.post(URL_BASE + URL_OPINION,requestParams,new JsonResponseHandler(aNetworkResponseListener), this.context);
    }

}
