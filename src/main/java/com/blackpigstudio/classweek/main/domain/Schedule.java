package com.blackpigstudio.classweek.main.domain;

import java.io.Serializable;

/**
 * Created by continueing on 2014. 4. 13..
 */
public class Schedule implements Serializable {
    public static final int DAY_SCHEDULE_TYPE = 0;
    public static final int MONTH_SCHEDULE_TYPE = 1;

    private int number;
    private int type;
    private int price;
    private int numberOfClasses;
    private int month;
    private int day;
    private int start_hour;
    private int start_minute;
    private int end_hour;
    private int end_minute;
}
