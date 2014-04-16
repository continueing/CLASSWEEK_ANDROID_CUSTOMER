package com.blackpigstudio.classweek.main.domain.class_info;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by continueing on 2014. 4. 16..
 */
public class ClassDetailInfo {
    public static final String JSON_KEY_DETAIL_IMAGE_URLS = "detail_image_urls";
    public static final String JSON_KEY_NUMBER_OF_CLASS_PER_MONTH = "number_of_class_per_month";
    public static final String JSON_KEY_ADDRESS = "address";
    public static final String JSON_KEY_DESCRIPTION = "description";
    public static final String JSON_KEY_PREREQUISITE = "prerequisite";
    public static final String JSON_KEY_REFUND_INFO = "refund_info";


    private int classId;
    private ArrayList<String> imageUrls;
    private int numberOfClassPerMonth;
    private String address;
    private String descriptions;
    private String prerequisite;
    private String refundInfo;


    public ClassDetailInfo(JSONObject aJsonObject) throws JSONException {
        this.classId = aJsonObject.getInt(ClassInfo.JSON_KEY_CLASS_ID);
        this.imageUrls = new ArrayList<String>();
        JSONArray jsonDetailImageArray = aJsonObject.getJSONArray(JSON_KEY_DETAIL_IMAGE_URLS);
        for(int i = 0 ; i < jsonDetailImageArray.length() ; i++)
        {
            imageUrls.add(jsonDetailImageArray.getString(i));
        }
        this.numberOfClassPerMonth = aJsonObject.getInt(JSON_KEY_NUMBER_OF_CLASS_PER_MONTH);
        this.address = aJsonObject.getString(JSON_KEY_ADDRESS);
        this.descriptions = aJsonObject.getString(JSON_KEY_DESCRIPTION);
        this.prerequisite= aJsonObject.getString(JSON_KEY_PREREQUISITE);
        this.refundInfo = aJsonObject.getString(JSON_KEY_REFUND_INFO);
    }

    public int getClassId()
    {
        return this.classId;
    }

    public String getDetailImageUrlAt(int anIndex)
    {
        return this.imageUrls.get(anIndex);
    }

    public int getNumberOfClassPerMonth()
    {
        return this.numberOfClassPerMonth;
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




}
