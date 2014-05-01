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
    public static final String JSON_KEY_SCHEDULE_ID = "schedule_id";
    public static final String JSON_KEY_FRONT_IMAGE_URL = "image_url";
    public static final String JSON_KEY_TITLE = "title";
    public static final String JSON_KEY_DURATION = "duration";
    public static final String JSON_KEY_COMPANY = "company";
    public static final String JSON_KEY_TIMES = "times";
    public static final String JSON_KEY_SHORT_LOCATION = "nearby_station";
    public static final String JSON_KEY_ONE_DAY_PRICE = "price_of_day";
    public static final String JSON_KEY_ONE_MONTH_PRICE = "price_of_month";
    public static final String JSON_KEY_DISCOUNT_RATIO = "discount_rate";
    public static final String JSON_KEY_NUMBER_OF_CLASS_PER_MONTH = "count_of_month";


    private int scheduleId;
    private String front_image_url;
    private String title;
    private String duration;
    private String company;
    private ArrayList<String> times;
    private int one_day_price;
    private int one_month_price;
    private String shortLocation;
    private int discountRatio = 20;
    private int classId;
    private int numberOfClassPerMonth;


    public ClassSummaryInfo(int aPrice)
    {
        this.times = new ArrayList<String>();
        this.one_day_price = aPrice;
    }

    public ClassSummaryInfo(JSONObject aJsonObject) throws JSONException {
        this.scheduleId = aJsonObject.getInt(JSON_KEY_SCHEDULE_ID);
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
        this.numberOfClassPerMonth = aJsonObject.getInt(JSON_KEY_NUMBER_OF_CLASS_PER_MONTH);
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

    public ArrayList<String> getTimes()
    {
        return this.times;
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
    public int getDiscountRatio() {
        return this.discountRatio;
    }

    @Override
    public String getFrontImageUrl() {
        return this.front_image_url;
    }

    @Override
    public int getScheduleId() {
        return this.scheduleId;
    }

    @Override
    public int getNumberOfClassPerMonth() {
        return this.numberOfClassPerMonth;
    }

    public String getDuration()
    {
        return this.duration;
    }

}
