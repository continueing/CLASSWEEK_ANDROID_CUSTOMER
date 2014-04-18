package com.blackpigstudio.classweek.main.domain;

import com.blackpigstudio.classweek.main.ui.menu.home.subcategory.ViewForSubcategoryListViewItem;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by continueing on 2014. 4. 7..
 */
public class Subcategory implements ViewForSubcategoryListViewItem.ISubcategory{
    public static final String JSON_KEY_TITLE_ENG = "name";
    public static final String JSON_KEY_TITLE_KOR = "name_kor";
    public static final String JSON_KEY_FRONT_IMAGE_URL = "image_url";
    public static final String JSON_KEY_DESCRIPTION = "description";

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
    public String getKorTitle() {
        return this.title_kor;
    }

    @Override
    public String getFrontImageUrl() {
        return this.frontImageUrl;
    }

    @Override
    public String getEngTitle() {
        return this.title_eng;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
