package com.blackpigstudio.classweek.main.domain.class_info;

import com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.review.ViewForReviewItem;

/**
 * Created by continueing on 2014. 5. 22..
 */
public class Review implements ViewForReviewItem.IReviewItem
{
    public Review()
    {

    }

    private int rank=1;
    private String contents="kkk";
    private String writtenDate="오늘 ㅎㅎ";


    public int getRank() {
        return rank;
    }

    public String getContents() {
        return contents;
    }

    public String getWrittenDate() {
        return writtenDate;
    }

}
