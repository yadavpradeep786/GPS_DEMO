package com.example.gpsdemo.utils;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastWaypoint implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("createTime")
    @Expose
    private Long createTime;
    @SerializedName("accuracy")
    @Expose
    private String accuracy;
    @SerializedName("bearing")
    @Expose
    private String bearing;
    @SerializedName("truckId")
    @Expose
    private String truckId;
    @SerializedName("speed")
    @Expose
    private String speed;
    @SerializedName("updateTime")
    @Expose
    private Long updateTime;
    @SerializedName("ignitionOn")
    @Expose
    private Boolean ignitionOn;
    @SerializedName("odometerReading")
    @Expose
    private String odometerReading;
    @SerializedName("batteryPower")
    @Expose
    private Boolean batteryPower;
    @SerializedName("fuelLevel")
    @Expose
    private String fuelLevel;
    @SerializedName("batteryLevel")
    @Expose
    private String batteryLevel;
    public final static Creator<LastWaypoint> CREATOR = new Creator<LastWaypoint>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LastWaypoint createFromParcel(android.os.Parcel in) {
            return new LastWaypoint(in);
        }

        public LastWaypoint[] newArray(int size) {
            return (new LastWaypoint[size]);
        }

    };

    protected LastWaypoint(android.os.Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.lat = ((String) in.readValue((String.class.getClassLoader())));
        this.lng = ((String) in.readValue((String.class.getClassLoader())));
        this.createTime = ((Long) in.readValue((Long.class.getClassLoader())));
        this.accuracy = ((String) in.readValue((String.class.getClassLoader())));
        this.bearing = ((String) in.readValue((String.class.getClassLoader())));
        this.truckId = ((String) in.readValue((String.class.getClassLoader())));
        this.speed = ((String) in.readValue((String.class.getClassLoader())));
        this.updateTime = ((Long) in.readValue((Long.class.getClassLoader())));
        this.ignitionOn = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.odometerReading = ((String) in.readValue((String.class.getClassLoader())));
        this.batteryPower = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.fuelLevel = ((String) in.readValue((String.class.getClassLoader())));
        this.batteryLevel = ((String) in.readValue((String.class.getClassLoader())));
    }

    public LastWaypoint() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getBearing() {
        return bearing;
    }

    public void setBearing(String bearing) {
        this.bearing = bearing;
    }

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIgnitionOn() {
        return ignitionOn;
    }

    public void setIgnitionOn(Boolean ignitionOn) {
        this.ignitionOn = ignitionOn;
    }

    public String getOdometerReading() {
        return odometerReading;
    }

    public void setOdometerReading(String odometerReading) {
        this.odometerReading = odometerReading;
    }

    public Boolean getBatteryPower() {
        return batteryPower;
    }

    public void setBatteryPower(Boolean batteryPower) {
        this.batteryPower = batteryPower;
    }

    public String getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(String fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public String getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(String batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(lat);
        dest.writeValue(lng);
        dest.writeValue(createTime);
        dest.writeValue(accuracy);
        dest.writeValue(bearing);
        dest.writeValue(truckId);
        dest.writeValue(speed);
        dest.writeValue(updateTime);
        dest.writeValue(ignitionOn);
        dest.writeValue(odometerReading);
        dest.writeValue(batteryPower);
        dest.writeValue(fuelLevel);
        dest.writeValue(batteryLevel);
    }

    public int describeContents() {
        return 0;
    }

}
