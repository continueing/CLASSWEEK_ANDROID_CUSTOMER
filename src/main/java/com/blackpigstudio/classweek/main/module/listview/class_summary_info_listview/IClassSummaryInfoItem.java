package com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview;

import com.blackpigstudio.classweek.main.module.listview.IListViewItem;

/**
 * Created by continueing on 2014. 4. 13..
 */
public interface IClassSummaryInfoItem extends IListViewItem {
    public int getClassId();
    public String getTitle();
    public String getTime();
    public String getCompany();
    public String getLocation();
    public String getDayPrice();
    public String getMonthPrice();
    public double getDiscountRatio();
    public String getURLOfImage();
}
