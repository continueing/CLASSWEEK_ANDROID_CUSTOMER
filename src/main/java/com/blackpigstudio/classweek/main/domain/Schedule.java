package com.blackpigstudio.classweek.main.domain;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by continueing on 2014. 4. 13..
 */
public class Schedule implements Serializable {
    public static final int DAY_SCHEDULE_TYPE = 0;
    public static final int MONTH_SCHEDULE_TYPE = 1;

    public static final String JSON_KEY_START_DATE_TIME = "start_date_time";
    public static final String JSON_KEY_START_DATE_TIME_FOR_PAYMENT = "start_date_time_unprocessed";
    public static final String JSON_KEY_END_DATE_TIME = "end_date_time";
    public static final String JSON_KEY_END_DATE_TIME_FOR_PAYMENT = "end_date_time_unprocessed";


    private int type;
    private String startDateTime;
    private String endDateTime;
    private String startDateTimeForPayment;
    private String endDateTimeForPayment;


    public Schedule(JSONObject aJsonObject) throws JSONException {
        this.type = MONTH_SCHEDULE_TYPE;
        this.startDateTime = aJsonObject.getString(JSON_KEY_START_DATE_TIME);
        this.endDateTime = aJsonObject.getString(JSON_KEY_END_DATE_TIME);
        this.startDateTimeForPayment = aJsonObject.getString(JSON_KEY_START_DATE_TIME_FOR_PAYMENT);
        this.endDateTimeForPayment = aJsonObject.getString(JSON_KEY_END_DATE_TIME_FOR_PAYMENT);
    }

    public String getStartDateTime()
    {
        return this.startDateTime;
    }

    public String getEndDateTime()
    {
        return this.endDateTime;
    }

    public String getStartDateTimeForPayment() {
        return startDateTimeForPayment;
    }

    public String getEndDateTimeForPayment() {
        return endDateTimeForPayment;
    }







}
