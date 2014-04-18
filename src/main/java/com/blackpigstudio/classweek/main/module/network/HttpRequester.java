package com.blackpigstudio.classweek.main.module.network;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

/**
 * Created by continueing on 2014. 4. 6..
 */
public class HttpRequester {
    private static final String BASE_URL = "http://175.126.82.184";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.i("", "url: "+url + "\nParms: " + params.toString());
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }


    public static interface NetworkResponseListener
    {
        public void onSuccess(JSONObject jsonObject);

        public void onFail(JSONObject jsonObject, int errorCode);

    }
}
