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
    public static final String JSON_KEY_END_DATE_TIME = "end_date_time";

    private int type;
    private String startDateTime;
    private String endDateTime;

    public Schedule(int aId, int aType, String aStartDateTime, String anEndDateTime)
    {
        this.type = aType;
        this.startDateTime = aStartDateTime;
        this.endDateTime = anEndDateTime;
    }

    public Schedule(JSONObject aJsonObject) throws JSONException {
        this.type = MONTH_SCHEDULE_TYPE;
        this.startDateTime = aJsonObject.getString(JSON_KEY_START_DATE_TIME);
        this.endDateTime = aJsonObject.getString(JSON_KEY_END_DATE_TIME);
    }

    public String getStartDateTime()
    {
        return this.startDateTime;
    }

    public String getEndDateTime()
    {
        return this.endDateTime;
    }







}
