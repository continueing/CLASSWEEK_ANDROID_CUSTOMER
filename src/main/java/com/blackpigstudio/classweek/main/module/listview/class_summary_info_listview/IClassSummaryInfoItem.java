package com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview;

import com.blackpigstudio.classweek.main.module.listview.mvc_base.IListViewItem;

/**
 * Created by continueing on 2014. 4. 13..
 */
public interface IClassSummaryInfoItem extends IListViewItem {
    public int getClassId();
    public String getTitle();
    public String getTime();
    public String getCompany();
    public String getShortLocation();
    public String getOneDayPrice();
    public String getOneMonthDiscountPrice();
    public String getOneMonthOriginalPrice();
    public int getDiscountRatio();
    public String getFrontImageUrl();
    public int getScheduleId();
    public int getNumberOfClassPerMonth();

}
