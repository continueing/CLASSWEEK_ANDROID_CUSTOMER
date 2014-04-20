package com.blackpigstudio.classweek.main.module.network;

import com.loopj.android.http.RequestParams;

import org.json.JSONException;

/**
 * Created by continueing on 2014. 4. 18..
 */
public class ClassRequest {
    private static final String URL_BASE = "/classes";
    private static final String URL_INQUIRE = "/inquire";
    private static final String PARM_KEY_CONTENTS_INQUIRE = "content";

    public void getSubcategories(String aCategory, HttpRequester.NetworkResponseListener aNetworkResponseListener) throws JSONException
    {
        RequestParams requestParams = new RequestParams();
        HttpRequester.post(URL_BASE +"/"+aCategory,requestParams,new JsonResponseHandler(aNetworkResponseListener));
    }

    public void getClassSummaryInfos(String aCategory, String aSubcategory, int aPage, HttpRequester.NetworkResponseListener aNetworkResponseListener) throws JSONException
    {
        RequestParams requestParams = new RequestParams();
        HttpRequester.post(URL_BASE +"/"+aCategory + "/" + aSubcategory + "/"+ aPage,requestParams,new JsonResponseHandler(aNetworkResponseListener));
    }

    public void getClassDetail(int aClassId, int aScheduleId, HttpRequester.NetworkResponseListener aNetworkResponseListener) throws JSONException
    {
        RequestParams requestParams = new RequestParams();
        HttpRequester.post(URL_BASE +"/"+aClassId + "/" + aScheduleId ,requestParams,new JsonResponseHandler(aNetworkResponseListener));
    }

    public void inquire(int aClassId, int aScheduleId, String aContents, HttpRequester.NetworkResponseListener aNetworkResponseListener) throws JSONException
    {

        RequestParams requestParams = new RequestParams();
        requestParams.put(PARM_KEY_CONTENTS_INQUIRE, aContents);
        HttpRequester.post(URL_BASE +"/"+aClassId + URL_INQUIRE ,requestParams,new JsonResponseHandler(aNetworkResponseListener));
    }

}
