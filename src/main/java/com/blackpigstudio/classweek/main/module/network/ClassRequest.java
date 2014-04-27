package com.blackpigstudio.classweek.main.module.network;

import android.content.Context;

import com.loopj.android.http.RequestParams;

import org.json.JSONException;

/**
 * Created by continueing on 2014. 4. 18..
 */
public class ClassRequest {
    private static final String URL_BASE = "/classes";
    private static final String URL_INQUIRE = "/inquire";
    private static final String URL_RECOMMEND_CLASSES = "/recommend/classes";

    private static final String PARM_KEY_CONTENTS_INQUIRE = "content";
    private static final String PARM_KEY_WEEKDAY = "weekday";
    private static final String PARM_KEY_LOCATION = "location";
    private static final String PARM_KEY_TIME = "time";
    private static final String PARM_KEY_PRICE = "price";



    private Context context;

    public ClassRequest(Context aContext)
    {
        this.context = aContext;
    }

    public void getSubcategories(String aCategory, HttpRequester.NetworkResponseListener aNetworkResponseListener) throws JSONException
    {
        RequestParams requestParams = new RequestParams();
        HttpRequester.post(URL_BASE +"/"+aCategory,requestParams,new JsonResponseHandler(aNetworkResponseListener), this.context);
    }

    public void getClassSummaryInfos(String aCategory, String aSubcategory, String aWeekDay, String aLocation, String aTime, String aPrice, int aPage, HttpRequester.NetworkResponseListener aNetworkResponseListener) throws JSONException
    {
        RequestParams requestParams = new RequestParams();
        if(aWeekDay != null)
            requestParams.put(PARM_KEY_WEEKDAY, aWeekDay);
        if(aLocation != null)
            requestParams.put(PARM_KEY_LOCATION, aLocation);
        if(aTime != null)
            requestParams.put(PARM_KEY_TIME, aTime);
        if(aPrice != null)
            requestParams.put(PARM_KEY_PRICE, aPrice);

        HttpRequester.post(URL_BASE +"/"+aCategory + "/" + aSubcategory + "/"+ aPage,requestParams,new JsonResponseHandler(aNetworkResponseListener), this.context);
    }

    public void getRecommendedClassSummaryInfos(HttpRequester.NetworkResponseListener aNetworkResponseListener) throws JSONException
    {
        RequestParams requestParams = new RequestParams();
        HttpRequester.post(URL_BASE + URL_RECOMMEND_CLASSES ,requestParams,new JsonResponseHandler(aNetworkResponseListener), this.context);
    }

    public void getClassDetail(int aClassId, int aScheduleId, HttpRequester.NetworkResponseListener aNetworkResponseListener) throws JSONException
    {
        RequestParams requestParams = new RequestParams();
        HttpRequester.post(URL_BASE +"/"+aClassId + "/" + aScheduleId ,requestParams,new JsonResponseHandler(aNetworkResponseListener), this.context);
    }

    public void inquire(int aClassId, String aContents, HttpRequester.NetworkResponseListener aNetworkResponseListener) throws JSONException
    {

        RequestParams requestParams = new RequestParams();
        requestParams.put(PARM_KEY_CONTENTS_INQUIRE, aContents);
        HttpRequester.post(URL_BASE +"/"+aClassId + URL_INQUIRE ,requestParams,new JsonResponseHandler(aNetworkResponseListener), this.context);
    }



}
