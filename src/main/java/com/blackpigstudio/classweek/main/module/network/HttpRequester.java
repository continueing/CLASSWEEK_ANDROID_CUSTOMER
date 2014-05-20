package com.blackpigstudio.classweek.main.module.network;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.apache.http.cookie.Cookie;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by continueing on 2014. 4. 6..
 */
public class HttpRequester {
    private static final String BASE_URL = "http://175.126.82.184";

    private static AsyncHttpClient client = new AsyncHttpClient();


    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler, Context aContext) {
        Log.i("request", "Url: "+url);
        Log.i("request", "Parms: " + params.toString());
        PersistentCookieStore persistentCookieStore = new PersistentCookieStore(aContext);
        client.setCookieStore(persistentCookieStore);
        client.post(getAbsoluteUrl(url), params, responseHandler);
        List<Cookie> cookieList =  persistentCookieStore.getCookies();
        for( Cookie aCookie : cookieList)
        {
            Log.i("name", aCookie.getName());
            Log.i("value", aCookie.getValue());
        }
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
