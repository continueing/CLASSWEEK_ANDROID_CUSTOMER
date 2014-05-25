package com.blackpigstudio.classweek.main.domain.class_info;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by continueing on 2014. 4. 16..
 */
public class ClassDetailInfo implements Serializable {
    public static final String JSON_KEY_DETAIL_IMAGE_URLS = "detail_image_url";
    public static final String JSON_KEY_ADDRESS = "address";
    public static final String JSON_KEY_DESCRIPTION = "description";
    public static final String JSON_KEY_PREREQUISITE = "preparation";
    public static final String JSON_KEY_REFUND_INFO = "refund_info";
    public static final String JSON_KEY_LESSON_TYPE= "lesson_type";
    public static final String JSON_KEY_NUM_OF_CLASS_PER_WEEK= "count_of_week";
    public static final String JSON_KEY_CURRICULUM_WEEK_1 = "curriculum_in_first_week";
    public static final String JSON_KEY_CURRICULUM_WEEK_2 = "curriculum_in_second_week";
    public static final String JSON_KEY_CURRICULUM_WEEK_3 = "curriculum_in_third_week";
    public static final String JSON_KEY_CURRICULUM_WEEK_4 = "curriculum_in_fourth_week";
    public static final String JSON_KEY_COMPANY_INTRODUCTION = "company_introduction";

    private int classId;
    private ArrayList<String> imageUrls;
    private String address;
    private String descriptions;
    private String prerequisite;
    private String refundInfo;
    private String lessonType;
    private int numOfClassPerWeek;
    private String curriculum_week_1;
    private String curriculum_week_2;
    private String curriculum_week_3;
    private String curriculum_week_4;
    private String companyIntroduction;



    public ClassDetailInfo(JSONObject aJsonObject) throws JSONException {
        this.classId = aJsonObject.getInt(ClassInfo.JSON_KEY_CLASS_ID);
        this.imageUrls = new ArrayList<String>();
        JSONArray jsonDetailImageArray = aJsonObject.getJSONArray(JSON_KEY_DETAIL_IMAGE_URLS);
        for(int i = 0 ; i < jsonDetailImageArray.length() ; i++)
        {
            imageUrls.add(jsonDetailImageArray.getString(i));
        }
        this.address = aJsonObject.getString(JSON_KEY_ADDRESS);
        this.descriptions = aJsonObject.getString(JSON_KEY_DESCRIPTION);
        this.prerequisite= aJsonObject.getString(JSON_KEY_PREREQUISITE);
        this.refundInfo = aJsonObject.getString(JSON_KEY_REFUND_INFO);
        this.lessonType = aJsonObject.getString(JSON_KEY_LESSON_TYPE);
        this.numOfClassPerWeek= aJsonObject.getInt(JSON_KEY_NUM_OF_CLASS_PER_WEEK);
        this.curriculum_week_1 = aJsonObject.getString(JSON_KEY_CURRICULUM_WEEK_1);
        this.curriculum_week_2 = aJsonObject.getString(JSON_KEY_CURRICULUM_WEEK_2);
        this.curriculum_week_3 = aJsonObject.getString(JSON_KEY_CURRICULUM_WEEK_3);
        this.curriculum_week_4 = aJsonObject.getString(JSON_KEY_CURRICULUM_WEEK_4);
        this.companyIntroduction = aJsonObject.getString(JSON_KEY_COMPANY_INTRODUCTION);
    }

    public int getClassId()
    {
        return this.classId;
    }

    public String getDetailImageUrlAt(int anIndex)
    {
        return this.imageUrls.get(anIndex);
    }

    public String getAddress() {
        return this.address;
    }

    public String getDescriptions() {
        return this.descriptions;
    }

    public String getPrerequisite() {
        return this.prerequisite;
    }

    public String getRefundInfo() {
        return this.refundInfo;
    }

    public ArrayList<String> getDetailImageUrl()
    {
        ArrayList<String> result = new ArrayList<String>();
        result.addAll(this.imageUrls);
        return result;
    }

    public String getLessonType()
    {
        return this.lessonType;
    }

    public int getNumOfClassPerWeek()
    {
        return this.numOfClassPerWeek;
    }

    public String getCurriculum_week_1() {
        return curriculum_week_1;
    }

    public String getCurriculum_week_2() {
        return curriculum_week_2;
    }

    public String getCurriculum_week_3() {
        return curriculum_week_3;
    }

    public String getCurriculum_week_4() {
        return curriculum_week_4;
    }

    public String getCompanyIntroduction() {
        return companyIntroduction;
    }
}
