package com.blackpigstudio.classweek.main.module.network;

import android.content.Context;

import com.loopj.android.http.RequestParams;

import org.json.JSONException;

/**
 * Created by continueing on 2014. 4. 11..
 */
public class UserRequest {
    private static String URL_BASE = "/user";
    private static String URL_LOGIN =  "/login";
    private static String URL_SIGN_UP =  "/registration";
    private static String URL_UPDATE =  "/update";
    private static String URL_CONTACT =  "/inquire";
    private static String URL_LOGOUT =  "/logout";
    private Context context;

    public static String PARM_EMAIL = "email";
    public static String PARM_PASSWORD = "password";
    public static String PARM_PASSWORD_CONFIRM = "password_confirm";

    public static String PARM_NAME = "name";
    public static String PARM_BIRTH_DAY = "birthday";
    public static String PARM_PHONE_NUMBER = "phone_number";
    public static String PARM_GENDER = "gender";
    public static String PARM_CONTENTS = "inquire_content";



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
        HttpRequester.post(URL_BASE + URL_SIGN_UP,requestParams,new JsonResponseHandler(aNetworkResponseListener), this.context);
    }

    public void update(String aName, String aBirthDay, String aPhone_number, String aGender, final HttpRequester.NetworkResponseListener aNetworkResponseListener) throws JSONException
    {
        RequestParams requestParams = new RequestParams();
        requestParams.put(PARM_NAME, aName);
        requestParams.put(PARM_BIRTH_DAY, aBirthDay);
        requestParams.put(PARM_PHONE_NUMBER,aPhone_number);
        requestParams.put(PARM_GENDER,aGender);
        HttpRequester.post(URL_BASE + URL_UPDATE,requestParams,new JsonResponseHandler(aNetworkResponseListener), this.context);
    }

    public void contact(String aContents, final HttpRequester.NetworkResponseListener aNetworkResponseListener) throws JSONException
    {
        RequestParams requestParams = new RequestParams();
        requestParams.put(PARM_CONTENTS, aContents);
        HttpRequester.post(URL_BASE + URL_CONTACT,requestParams,new JsonResponseHandler(aNetworkResponseListener), this.context);
    }

    public void logout( final HttpRequester.NetworkResponseListener aNetworkResponseListener) throws JSONException
    {
        RequestParams requestParams = new RequestParams();
        HttpRequester.post(URL_BASE + URL_LOGOUT,requestParams,new JsonResponseHandler(aNetworkResponseListener), this.context);
    }
}
