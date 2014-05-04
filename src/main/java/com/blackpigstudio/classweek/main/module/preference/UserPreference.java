package com.blackpigstudio.classweek.main.module.preference;

import android.content.Context;

/**
 * Created by continueing on 2014. 4. 11..
 */
public class UserPreference extends PreferenceHelper {
    private static final String FILE_NAME ="CLASSWEEK_USER";

    private static final String KEY_NAME ="NAME";
    private static final String KEY_BIRTH_DATE ="BIRTH_DATE"; // YYYY-MM-dd
    private static final String KEY_SEX ="SEX";
    private static final String KEY_PHONE_NUMBER ="PHONE_NUMBER"; // 010-4527-9272
    private static final String KEY_EMAIL ="EMAIL";
    private static final String KEY_PASSWORD ="PASSWORD";
    private static final String KEY_IS_LOGIN ="IS_LOGIN";


    public static final int VALUE_SEX_MALE = 1;
    public static final int VALUE_SEX_FEMALE = 2;

    public UserPreference(Context aContext) {
        super(aContext);
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }

    /*
        account
     */

    public void login(String anEmail, String aPassword)
    {
        setEmail(anEmail);
        setPassword(aPassword);
        setBoolean(KEY_IS_LOGIN, true);
    }

    public void logout()
    {
        setBoolean(KEY_IS_LOGIN, false);
    }

    /*
        setter
     */

    public void setEmail(String anEmail)
    {
        setString(KEY_EMAIL, anEmail);
    }

    public void setPassword(String aPassword)
    {
        setString(KEY_PASSWORD, aPassword);
    }

    public void setName(String aName)
    {
        setString(KEY_NAME,aName);
    }

    public void setBirthDate(String aBirthDate)
    {
        setString(KEY_BIRTH_DATE, aBirthDate);
    }

    public void setSex(int aValueOfSex)
    {
        setInt(KEY_SEX,aValueOfSex);
    }

    public void setPhoneNumber(String aPhoneNumber)
    {
        setString(KEY_PHONE_NUMBER, aPhoneNumber);
    }

   /*
        getter
    */
    public String getName()
    {
        return getString(KEY_NAME, "");
    }

    public String getBirth()
    {
        return  getString(KEY_BIRTH_DATE,"");
    }

    public int getSex()
    {
        return getInt(KEY_SEX, VALUE_SEX_FEMALE);
    }

    public String getPhoneNumber()
    {
        return getString(KEY_PHONE_NUMBER, "");
    }

    public boolean isLoggedIn()
    {
        return getBoolean(KEY_IS_LOGIN, false);
    }

    public String getEmail()
    {
        return getString(KEY_EMAIL, null);
    }

    public String getPassword()
    {
        return getString(KEY_PASSWORD, null);
    }

}
