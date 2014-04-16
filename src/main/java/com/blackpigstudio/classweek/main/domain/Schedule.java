package com.blackpigstudio.classweek.main.domain;

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

    private int id;
    private int type;
    private String startDateTime;
    private String endDateTime;

    public Schedule(int anId, int aType, String aStartDateTime, String anEndDateTime)
    {
        this.id = anId;
        this.type = aType;
        this.startDateTime = aStartDateTime;
        this.endDateTime = anEndDateTime;
    }

    public Schedule(JSONObject aJsonObject)
    {

    }

    public int getId()
    {
        return this.id;
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
