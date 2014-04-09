package com.blackpigstudio.classweek.main.domain;

import com.blackpigstudio.classweek.main.ui.home.subcategory.ViewForSubcategoryListViewItem;

/**
 * Created by continueing on 2014. 4. 7..
 */
public class Subcategory implements ViewForSubcategoryListViewItem.ISubcategory{

    @Override
    public String getTitle() {
        return "kkk";
    }

    @Override
    public String getFrontImageUrl() {
        return "http://cfs9.tistory.com/upload_control/download.blog?fhandle=YmxvZzYzOTUyQGZzOS50aXN0b3J5LmNvbTovYXR0YWNoLzAvMTkwMDAwMDAwMDAwLmpwZw%3D%3D";
    }
}
