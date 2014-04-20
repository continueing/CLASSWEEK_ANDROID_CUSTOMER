package com.blackpigstudio.classweek.main.module.network;

import android.content.Context;

import com.blackpigstudio.classweek.main.module.AppTerminator;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by continueing on 2014. 4. 11..
 */
public class UserRequest {
    private static String URL_BASE = "/user";
    private static String URL_LOGIN =  "/login";
    private static String URL_SINGUP =  "/registration";
    private Context context;

    public static String PARM_EMAIL = "email";
    public static String PARM_PASSWORD = "password";
    public static String PARM_PASSWORD_CONFIRM = "password_confirm";


    public UserRequest(Context aContext)
    {
        this.context = aContext;
    }

    public void login(String anEmail, String aPassword, final HttpRequester.NetworkResponseListener aNetworkResponseListener) throws JSONException
    {
        RequestParams requestParams = new RequestParams();
        requestParams.put(PARM_EMAIL, anEmail);
        requestParams.put(PARM_PASSWORD, aPassword);
        HttpRequester.post(URL_BASE +URL_LOGIN,requestParams,new JsonResponseHandler(aNetworkResponseListener), this.context);
    }

    public void signUp(String anEmail, String aPassword, String aPasswordConfirmation, final HttpRequester.NetworkResponseListener aNetworkResponseListener) throws JSONException
    {
        RequestParams requestParams = new RequestParams();
        requestParams.put(PARM_EMAIL, anEmail);
        requestParams.put(PARM_PASSWORD, aPassword);
        requestParams.put(PARM_PASSWORD_CONFIRM,aPasswordConfirmation);
        HttpRequester.post(URL_BASE +URL_SINGUP,requestParams,new JsonResponseHandler(aNetworkResponseListener), this.context);
    }

}
