package com.blackpigstudio.classweek.main.domain;

import com.blackpigstudio.classweek.main.ui.menu.now_taking.listview.ViewForNowTakingClassListViewItem;
import com.blackpigstudio.classweek.main.ui.menu.took_before.ViewForTookBeforeClassListViewItem;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by continueing on 2014. 4. 10..
 */
public class MyClass implements ViewForNowTakingClassListViewItem.INowTakingClass, ViewForTookBeforeClassListViewItem.ITookBeforeClass {
    public static final String JSON_KEY_TITLE = "title";
    public static final String JSON_KEY_TIME = "time";
    public static final String JSON_KEY_START_DATE_TIME = "start_datetime";
    public static final String JSON_KEY_END_DATE_TIME = "end_datetime";
    public static final String JSON_KEY_CURRENT_STATE = "current_state";
    public static final String JSON_KEY_FRONT_IMAGE_URL = "image_url";

    private String title;
    private String startDateTime;
    private String endDateTime;
    private String currentState;
    private String time;
    private String frontImageUrl;


    public MyClass(JSONObject aJsonObject) throws JSONException {
        title = aJsonObject.getString(JSON_KEY_TITLE);
        time = aJsonObject.getString(JSON_KEY_TIME);
        startDateTime = aJsonObject.getString(JSON_KEY_START_DATE_TIME);
        endDateTime = aJsonObject.getString(JSON_KEY_END_DATE_TIME);
        currentState = aJsonObject.getString(JSON_KEY_CURRENT_STATE);
        frontImageUrl = aJsonObject.getString(JSON_KEY_FRONT_IMAGE_URL);
    }


    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getStartDateTime() {
        return startDateTime;
    }

    @Override
    public String getEndDateTime() {
        return endDateTime;
    }

    @Override
    public String getCurrentState() {
        return currentState;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public String getFrontImageUrl() {
        return frontImageUrl;
    }


}
