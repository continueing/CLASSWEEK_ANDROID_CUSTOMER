package com.blackpigstudio.classweek.main.domain.class_info;

import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.IClassSummaryInfoItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by continueing on 2014. 3. 30..
 */
public class ClassSummaryInfo  implements IClassSummaryInfoItem, Serializable{
    public static final String JSON_KEY_TITLE = "title";
    public static final String JSON_KEY_TIMES = "times";
    public static final String JSON_KEY_DURATION = "duration";
    public static final String JSON_KEY_COMPANY = "company";
    public static final String JSON_KEY_SHORT_LOCATION = "short_location";
    public static final String JSON_KEY_ONE_DAY_PRICE = "one_day_price";
    public static final String JSON_KEY_ONE_MONTH_PRICE = "one_month_price";
    public static final String JSON_KEY_DISCOUNT_RATIO = "discount_ratio";
    public static final String JSON_KEY_FRONT_IMAGE_URL = "front_image_url";

    private int classId = 1;
    private String title ="A학원 속성 줌바";
    private ArrayList<String> times;
    private String duration = "1시간 30분";
    private String company = "헤이브래드";
    private String shortLocation = "역삼역";
    private int one_day_price = 12000;
    private int one_month_price = 200000;
    private int discountRatio = 20;
    private String front_image_url = "http://img.lifestyler.co.kr/uploadfiles/user/review//201304/file7814092940901153310.jpg" ;

    public ClassSummaryInfo(int aPrice)
    {
        this.times = new ArrayList<String>();
        this.one_day_price = aPrice;
    }

    public ClassSummaryInfo(JSONObject aJsonObject) throws JSONException {
        this.classId = aJsonObject.getInt(ClassInfo.JSON_KEY_CLASS_ID);
        this.title = aJsonObject.getString(JSON_KEY_TITLE);
        this.times = new ArrayList<String>();
        JSONArray jsonTimeArray = aJsonObject.getJSONArray(JSON_KEY_TIMES);
        for(int i = 0 ; i < jsonTimeArray.length() ; i ++)
        {
            this.times.add(jsonTimeArray.getString(i));
        }
        this.duration = aJsonObject.getString(JSON_KEY_DURATION);
        this.company = aJsonObject.getString(JSON_KEY_COMPANY);
        this.shortLocation = aJsonObject.getString(JSON_KEY_SHORT_LOCATION);
        this.one_day_price = aJsonObject.getInt(JSON_KEY_ONE_DAY_PRICE);
        this.one_month_price = aJsonObject.getInt(JSON_KEY_ONE_MONTH_PRICE);
        this.discountRatio = aJsonObject.getInt(JSON_KEY_DISCOUNT_RATIO);
        this.front_image_url = aJsonObject.getString(JSON_KEY_FRONT_IMAGE_URL);
    }

    @Override
    public int getClassId() {
        return this.classId;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getTime() {
        String result = "";
        for(String aTime : times)
            result += aTime + " ";
        return result;
    }

    @Override
    public String getCompany() {
        return this.company;
    }

    @Override
    public String getShortLocation() {
        return this.shortLocation;
    }

    @Override
    public String getOneDayPrice() {
        return String.valueOf(this.one_day_price);
    }

    @Override
    public String getOneMonthPrice() {
        return String.valueOf(this.one_month_price);
    }

    @Override
    public double getDiscountRatio() {
        return this.discountRatio;
    }

    @Override
    public String getFrontImageUrl() {
        return this.front_image_url;
    }



}
