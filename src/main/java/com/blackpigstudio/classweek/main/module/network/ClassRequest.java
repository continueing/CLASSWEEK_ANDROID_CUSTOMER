package com.blackpigstudio.classweek.main.module.network;

import com.loopj.android.http.RequestParams;

import org.json.JSONException;

/**
 * Created by continueing on 2014. 4. 18..
 */
public class ClassRequest {
    private static String URL_BASE = "/classes";

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
}
