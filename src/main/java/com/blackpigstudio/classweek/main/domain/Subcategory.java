package com.blackpigstudio.classweek.main.domain;

import com.blackpigstudio.classweek.main.ui.menu.home.subcategory.ViewForSubcategoryListViewItem;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by continueing on 2014. 4. 7..
 */
public class Subcategory implements ViewForSubcategoryListViewItem.ISubcategory{
    public static final String JSON_KEY_TITLE_ENG = "title_eng";
    public static final String JSON_KEY_TITLE_KOR = "title_kor";
    public static final String JSON_KEY_FRONT_IMAGE_URL = "front_image_url";
    public static final String JSON_KEY_DESCRIPTION = "title";

    private String title_eng;
    private String title_kor;
    private String frontImageUrl;
    private String description;

    public Subcategory()  {
    }

    public Subcategory(JSONObject aJsonObject) throws JSONException {
        this.title_eng = aJsonObject.getString(JSON_KEY_TITLE_ENG);
        this.title_kor = aJsonObject.getString(JSON_KEY_TITLE_KOR);
        this.frontImageUrl = aJsonObject.getString(JSON_KEY_FRONT_IMAGE_URL);
        this.description = aJsonObject.getString(JSON_KEY_DESCRIPTION);
    }

    @Override
    public String getTitle() {
        return "kkk";
    }

    @Override
    public String getFrontImageUrl() {
        return "http://cfs9.tistory.com/upload_control/download.blog?fhandle=YmxvZzYzOTUyQGZzOS50aXN0b3J5LmNvbTovYXR0YWNoLzAvMTkwMDAwMDAwMDAwLmpwZw%3D%3D";
    }

    @Override
    public String getSubCategoryName() {
        return "sala";
    }

    @Override
    public String getDescription() {
        return "우와앙";
    }
}
