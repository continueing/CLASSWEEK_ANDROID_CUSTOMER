package com.blackpigstudio.classweek.main.domain.class_info;

import com.blackpigstudio.classweek.main.ui.menu.home.class_detail_info.review.ViewForReviewItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by continueing on 2014. 5. 22..
 */
public class Review implements ViewForReviewItem.IReviewItem, Serializable
{
    public static final String JSON_KEY_RANK = "score";
    public static final String JSON_KEY_CONTENTS = "contents";
    public static final String JSON_KEY_WRITTEN_DATE = "datetime";

    private int rank;
    private String contents;
    private String writtenDate;


    public Review(JSONObject aJsonObject) throws JSONException
    {
       rank = aJsonObject.getInt(JSON_KEY_RANK);
       contents = aJsonObject.getString(JSON_KEY_CONTENTS);
       writtenDate = aJsonObject.getString(JSON_KEY_WRITTEN_DATE);
    }


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
