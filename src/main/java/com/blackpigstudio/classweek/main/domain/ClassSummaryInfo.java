package com.blackpigstudio.classweek.main.domain;

import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.IClassSummaryInfoItem;

import java.io.Serializable;

/**
 * Created by continueing on 2014. 3. 30..
 */
public class ClassSummaryInfo  implements IClassSummaryInfoItem, Serializable{
    private String title ="A학원 속성 줌바";
    private String time = "월 오후 7시, 수 오후 9시(1시간 30분)";
    private String company = "헤이브래드";
    private String location = "역삼역";
    private int day_price = 12000;
    private int month_price = 200000;
    private double discountRatio = 0.2;
    private String url = "http://img.lifestyler.co.kr/uploadfiles/user/review//201304/file7814092940901153310.jpg" ;
    private int id = 1;

    public ClassSummaryInfo(int aPrice)
    {
        this.day_price = aPrice;
    }

    @Override
    public int getClassId() {
        return this.id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getTime() {
        return this.time;
    }

    @Override
    public String getCompany() {
        return this.company;
    }

    @Override
    public String getLocation() {
        return this.location;
    }


    @Override
    public String getDayPrice() {
        return String.valueOf(this.day_price);
    }

    @Override
    public String getMonthPrice() {
        return String.valueOf(this.month_price);
    }

    @Override
    public double getDiscountRatio() {
        return 0;
    }

    @Override
    public String getURLOfImage() {
        return this.url;
    }



}
