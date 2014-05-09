package com.blackpigstudio.classweek.main.domain;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by continueing on 2014. 5. 5..
 */
public class InicisPaymentInfo implements Serializable {
    private static final String P_GOODS = "P_GOODS";
    private static final String P_RETURN_URL = "P_RETURN_URL";
    private static final String P_NEXT_URL = "P_NEXT_URL";
    private static final String P_MID = "P_MID";
    private static final String P_NOTI_URL = "P_NOTI_URL";
    private static final String P_AMT = "P_AMT";
    private static final String P_UNAME = "P_UNAME";
    private static final String P_NOTI = "P_NOTI";
    private static final String P_OID = "P_OID";


    private String mId;
    private int amount;
    private String userName;
    private String noti;
    private String nextUrl;
    private String notiUrl;
    private String returnUrl;
    private String goods;
    private String oId;

    public InicisPaymentInfo(JSONObject aJsonObject) throws JSONException {
        goods = aJsonObject.getString(P_GOODS);
        returnUrl = aJsonObject.getString(P_RETURN_URL);
        nextUrl = aJsonObject.getString(P_NEXT_URL);
        mId = aJsonObject.getString(P_MID);
        notiUrl = aJsonObject.getString(P_NOTI_URL);
        amount = aJsonObject.getInt(P_AMT);
        userName = aJsonObject.getString(P_UNAME);
        noti = aJsonObject.getJSONObject(P_NOTI).toString();
        oId = aJsonObject.getString(P_OID);
    }


    public String getMId() {
        return mId;
    }

    public int getAmount() {
        return amount;
    }

    public String getUserName() {
        return userName;
    }
    public String getNextUrl() {
        return nextUrl;
    }

    public String getNotiUrl() {
        return notiUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public String getGoods() {
        return goods;
    }
    public String getNoti() {
        return noti;
    }

    public String getOId()
    {
        return oId;
    }

}
