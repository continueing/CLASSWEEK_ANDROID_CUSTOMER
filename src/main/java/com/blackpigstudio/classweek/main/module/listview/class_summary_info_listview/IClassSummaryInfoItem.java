package com.blackpigstudio.classweek.main.module.listview.class_summary_info_listview;

import com.blackpigstudio.classweek.main.module.listview.IListViewItem;

/**
 * Created by continueing on 2014. 4. 13..
 */
public interface IClassSummaryInfoItem extends IListViewItem {
    public String getTitle();
    public String getHookSentence();
    public String getDetailLocation();
    public String getDayPrice();
    public String getMonthPrice();
    public String getURLOfImage();
}
