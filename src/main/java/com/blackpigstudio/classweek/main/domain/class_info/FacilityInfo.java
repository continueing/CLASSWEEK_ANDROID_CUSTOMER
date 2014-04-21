package com.blackpigstudio.classweek.main.domain.class_info;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by continueing on 2014. 4. 16..
 */
public class FacilityInfo implements Serializable {
    public static final String JSON_KEY_TOILET = "toilet";
    public static final String JSON_KEY_FITTING_ROOM = "fitting_room";
    public static final String JSON_KEY_SHOWER_STALL = "shower_stall";
    public static final String JSON_KEY_LOCKER = "locker";
    public static final String JSON_KEY_PARKING_LOT = "parking_lot";
    public static final String JSON_KEY_PRACTICE_ROOM = "practice_room";
    public static final String JSON_KEY_INSTRUMENT_RENTAL = "instrument_rental";

    private boolean toilet;
    private boolean fittingRoom;
    private boolean showerStall;
    private boolean locker;
    private boolean parkingLot;
    private boolean practiceRoom;
    private boolean instrumentRental;

    public FacilityInfo(JSONObject aJsonObject) throws JSONException {
        this.toilet = aJsonObject.getBoolean(JSON_KEY_TOILET);
        this.fittingRoom = aJsonObject.getBoolean(JSON_KEY_FITTING_ROOM);
        this.showerStall = aJsonObject.getBoolean(JSON_KEY_SHOWER_STALL);
        this.locker = aJsonObject.getBoolean(JSON_KEY_LOCKER);
        this.parkingLot= aJsonObject.getBoolean(JSON_KEY_PARKING_LOT);
        this.practiceRoom = aJsonObject.getBoolean(JSON_KEY_PRACTICE_ROOM);
        this.instrumentRental = aJsonObject.getBoolean(JSON_KEY_INSTRUMENT_RENTAL);
    }

    public boolean isToilet() {
        return toilet;
    }

    public boolean isFittingRoom() {
        return fittingRoom;
    }

    public boolean isShowerStall() {
        return showerStall;
    }

    public boolean isLocker() {
        return locker;
    }

    public boolean isParkingLot() {
        return parkingLot;
    }

    public boolean isPracticeRoom() {
        return practiceRoom;
    }

    public boolean isInstrumentRental() {
        return instrumentRental;
    }

}
