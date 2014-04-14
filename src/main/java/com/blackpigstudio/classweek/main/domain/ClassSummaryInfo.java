package com.blackpigstudio.classweek.main.domain;

import com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview.IClassSummaryInfoItem;

import java.io.Serializable;

/**
 * Created by continueing on 2014. 3. 30..
 */
public class ClassSummaryInfo  implements IClassSummaryInfoItem, Serializable{
    private String detail_location = "역삼역 2번 출구";
    private String hook_sentence = "몸짱이 될 수 있습니다.\n몸치를 탈출하세요.";
    private String title ="A학원 속성 줌바";
    private int day_price = 12000;
    private int month_price = 200000;
    private int classTimesOfWeek = 2;
    private String url = "http://img.lifestyler.co.kr/uploadfiles/user/review//201304/file7814092940901153310.jpg" ;
    private String detail_url = "http://img.lifestyler.co.kr/uploadfiles/user/review//201304/file7814092940901153310.jpg";

    public ClassSummaryInfo(int aPrice)
    {
        this.day_price = aPrice;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getHookSentence() {
        return this.hook_sentence;
    }

    @Override
    public String getDetailLocation() {
        return this.detail_location;
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
    public String getURLOfImage() {
        return this.url;
    }



}
