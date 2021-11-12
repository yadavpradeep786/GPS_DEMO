package com.example.gpsdemo.utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiveLocationItemObject implements Parcelable
{

    @SerializedName("GpsTId")
    @Expose
    private String gpsTId;
    @SerializedName("Imei")
    @Expose
    private String imei;
    @SerializedName("Speed")
    @Expose
    private String speed;
    @SerializedName("Lat")
    @Expose
    private String lat;
    @SerializedName("Lng")
    @Expose
    private String lng;
    @SerializedName("Odo")
    @Expose
    private String odo;
    @SerializedName("Ignition")
    @Expose
    private String ignition;
    @SerializedName("IPower")
    @Expose
    private String iPower;
    @SerializedName("Temp_Detect")
    @Expose
    private String tempDetect;
    @SerializedName("Fuel_Detect")
    @Expose
    private String fuelDetect;
    @SerializedName("Vib_Alarm")
    @Expose
    private String vibAlarm;
    @SerializedName("Over_Speed_Alarm")
    @Expose
    private String overSpeedAlarm;
    @SerializedName("DatedOn")
    @Expose
    private String datedOn;
    @SerializedName("SyncOn")
    @Expose
    private String syncOn;
    public final static Creator<LiveLocationItemObject> CREATOR = new Creator<LiveLocationItemObject>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LiveLocationItemObject createFromParcel(Parcel in) {
            return new LiveLocationItemObject(in);
        }

        public LiveLocationItemObject[] newArray(int size) {
            return (new LiveLocationItemObject[size]);
        }

    }
            ;

    protected LiveLocationItemObject(Parcel in) {
        this.gpsTId = ((String) in.readValue((String.class.getClassLoader())));
        this.imei = ((String) in.readValue((String.class.getClassLoader())));
        this.speed = ((String) in.readValue((String.class.getClassLoader())));
        this.lat = ((String) in.readValue((String.class.getClassLoader())));
        this.lng = ((String) in.readValue((String.class.getClassLoader())));
        this.odo = ((String) in.readValue((String.class.getClassLoader())));
        this.ignition = ((String) in.readValue((String.class.getClassLoader())));
        this.iPower = ((String) in.readValue((String.class.getClassLoader())));
        this.tempDetect = ((String) in.readValue((String.class.getClassLoader())));
        this.fuelDetect = ((String) in.readValue((String.class.getClassLoader())));
        this.vibAlarm = ((String) in.readValue((String.class.getClassLoader())));
        this.overSpeedAlarm = ((String) in.readValue((String.class.getClassLoader())));
        this.datedOn = ((String) in.readValue((String.class.getClassLoader())));
        this.syncOn = ((String) in.readValue((String.class.getClassLoader())));
    }

    public LiveLocationItemObject() {
    }

    public String getGpsTId() {
        return gpsTId;
    }

    public void setGpsTId(String gpsTId) {
        this.gpsTId = gpsTId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getOdo() {
        return odo;
    }

    public void setOdo(String odo) {
        this.odo = odo;
    }

    public String getIgnition() {
        return ignition;
    }

    public void setIgnition(String ignition) {
        this.ignition = ignition;
    }

    public String getIPower() {
        return iPower;
    }

    public void setIPower(String iPower) {
        this.iPower = iPower;
    }

    public String getTempDetect() {
        return tempDetect;
    }

    public void setTempDetect(String tempDetect) {
        this.tempDetect = tempDetect;
    }

    public String getFuelDetect() {
        return fuelDetect;
    }

    public void setFuelDetect(String fuelDetect) {
        this.fuelDetect = fuelDetect;
    }

    public String getVibAlarm() {
        return vibAlarm;
    }

    public void setVibAlarm(String vibAlarm) {
        this.vibAlarm = vibAlarm;
    }

    public String getOverSpeedAlarm() {
        return overSpeedAlarm;
    }

    public void setOverSpeedAlarm(String overSpeedAlarm) {
        this.overSpeedAlarm = overSpeedAlarm;
    }

    public String getDatedOn() {
        return datedOn;
    }

    public void setDatedOn(String datedOn) {
        this.datedOn = datedOn;
    }

    public String getSyncOn() {
        return syncOn;
    }

    public void setSyncOn(String syncOn) {
        this.syncOn = syncOn;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(gpsTId);
        dest.writeValue(imei);
        dest.writeValue(speed);
        dest.writeValue(lat);
        dest.writeValue(lng);
        dest.writeValue(odo);
        dest.writeValue(ignition);
        dest.writeValue(iPower);
        dest.writeValue(tempDetect);
        dest.writeValue(fuelDetect);
        dest.writeValue(vibAlarm);
        dest.writeValue(overSpeedAlarm);
        dest.writeValue(datedOn);
        dest.writeValue(syncOn);
    }

    public int describeContents() {
        return 0;
    }

}