package com.blackpigstudio.classweek.main.domain.class_info;

import com.blackpigstudio.classweek.main.domain.Schedule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by continueing on 2014. 4. 16..
 */
public class ClassInfo implements Serializable {
    public static final String JSON_KEY_CLASS_ID = "id";
    public static final String JSON_KEY_ONE_MONTH_SCHEDULES = "one_month_schedule";
    public static final String JSON_KEY_ = "";

    private int classId;
    private ClassSummaryInfo classSummaryInfo;
    private ClassDetailInfo classDetailInfo;
    private FacilityInfo facilityInfo;
    private ArrayList<Schedule> schedules;

    public ClassInfo(JSONObject aJsonObject) throws JSONException {

        this.classId = aJsonObject.getInt(JSON_KEY_CLASS_ID);
        this.classSummaryInfo = new ClassSummaryInfo(aJsonObject);
        this.classDetailInfo = new ClassDetailInfo(aJsonObject);
        this.facilityInfo = new FacilityInfo(aJsonObject);

        this.schedules= new ArrayList<Schedule>();
        JSONArray jsonScheduleArray = aJsonObject.getJSONArray(JSON_KEY_ONE_MONTH_SCHEDULES);
        for(int i = 0 ; i < jsonScheduleArray.length() ; i++)
        {
            this.schedules.add(new Schedule(jsonScheduleArray.getJSONObject(i)));
        }
    }

    public int getClassId()
    {
        return classId;
    }

    public ClassSummaryInfo getClassSummaryInfo() {
        return classSummaryInfo;
    }

    public ClassDetailInfo getClassDetailInfo() {
        return classDetailInfo;
    }

    public FacilityInfo getFacilityInfo() {
        return facilityInfo;
    }

    public Schedule getScheduleAt(int anIndex)
    {
       return schedules.get(anIndex);
    }


}
