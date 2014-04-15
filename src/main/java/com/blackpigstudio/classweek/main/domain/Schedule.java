package com.blackpigstudio.classweek.main.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by continueing on 2014. 4. 13..
 */
public class Schedule implements Serializable {
    public static final int DAY_SCHEDULE_TYPE = 0;
    public static final int MONTH_SCHEDULE_TYPE = 1;

    public Schedule(int aType, int aPrice, int aNumberOfClasses, String aDayOfWeek, int aMonth, int aDay, int aStartHour, int aStartMinute, int aEndHour, int aEndMinute)
    {
        this.type = aType;
        this.price = aPrice;
        this.numberOfClasses = aNumberOfClasses;
        this.dayOfWeek = aDayOfWeek;
        this.month = aMonth;
        this.day = aDay;
        this.startHour = aStartHour;
        this.startMinute = aStartMinute;
        this.endHour = aEndHour;
        this.endMinute = aEndMinute;
    }

    private int type;
    private int price;
    private int numberOfClasses; // 해당 스케쥴의 클래스는 몇번 인가

    private String dayOfWeek; // 요일
    private int month; // 수업 월
    private int day; // 수업 일
    private int startHour; // 시작 시간
    private int startMinute; // 시작 분
    private int endHour; // 끝 시간
    private int endMinute; // 끝 분


    public int type(){return this.type;}
    public int price(){return this.price;}
    public int numberOfClasses(){return this.numberOfClasses;}
    public String dayOfWeek(){return this.dayOfWeek;}
    public int month(){return this.month;}
    public int day(){return this.day;}
    public int startHour(){return this.startHour;}
    public int startMinute(){return this.startMinute;}
    public int endHour(){return this.endHour;}
    public int endMinute(){return this.endMinute;}

    public String toString()
    {
        String result="";
        result += toTimeString(this.month)+ "-" + toTimeString(this.day);
        result += "("+this.dayOfWeek()+") ";
        result += toTimeString(this.startHour) + ":" + toTimeString(this.startMinute) + "-";
        result += toTimeString(this.endHour) + ":" + toTimeString(this.endMinute);
        return result;
    }

    private String toTimeString(int aTime)
    {
        if(aTime < 10 )
            return "0" + aTime ;
        else
            return String.valueOf(aTime);
    }

}
