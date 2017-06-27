package com.jarusk.chunkyterm;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FitBitUser extends GenericJson {
    @Key
    private int age;

    @Key
    private String avatar;

    @Key
    private String avatar150;

    @Key
    private String avatar640;

    @Key
    private int averageDailySteps;

    @Key
    private String clockTimeDisplayFormat;

    @Key
    private boolean corporate;

    @Key
    private boolean corporateAdmin;

    @Key
    private String dateOfBirth;

    @Key
    private String displayName;

    @Key
    private String displayNameSetting;

    @Key
    private String distanceUnit;

    @Key
    private String encodeID;

    @Key
    private String firstName;

    @Key
    private String foodsLocale;

    @Key
    private String fullName;

    @Key
    private String gender;

    @Key
    private String glucoseUnit;

    @Key
    private int height;

    @Key
    private String heightUnit;

    @Key
    private String lastName;

    @Key
    private String locale;

    @Key
    private String memberSince;


    @Key
    private boolean mfaEnabled;

    @Key
    private int offsetFromUTCMillis;

    @Key
    private String startDayOfWeek;

    @Key
    private float strideLengthRunning;

    @Key
    private String  strideLengthRunningType;

    @Key
    private float strideLengthWalking;

    @Key
    private String strideLengthWalkingType;

    @Key
    private String swimUnit;

    @Key
    private String timezone;

    @Key
    private List<String> topBadges;

    @Key
    private String waterUnit;

    @Key
    private String waterUnitName;

    @Key
    private float weight;

    @Key
    private String weightUnit;



    public int getAge() {
        return age;
    }

    public int getAverageDailySteps() {
        return averageDailySteps;
    }

    public Date getDateOfBirth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date result;

        try {
            result = format.parse(dateOfBirth);
        }catch (ParseException e){
            result = null;
            System.err.println("Couldn't parse Date of Birth, returning NULL");
        }

        return result;
    }

    public String getFullName() {
        return fullName;
    }
}
