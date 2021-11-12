package com.example.gpsdemo.utils;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class LastRunningState implements Parcelable
{

    @SerializedName("truckId")
    @Expose
    private Integer truckId;
    @SerializedName("stopStartTime")
    @Expose
    private Long stopStartTime;
    @SerializedName("truckRunningState")
    @Expose
    private Integer truckRunningState;
    @SerializedName("lat")
    @Expose
    private Float lat;
    @SerializedName("lng")
    @Expose
    private Float lng;
    @SerializedName("stopNotficationSent")
    @Expose
    private Integer stopNotficationSent;
    public final static Creator<LastRunningState> CREATOR = new Creator<LastRunningState>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LastRunningState createFromParcel(android.os.Parcel in) {
            return new LastRunningState(in);
        }

        public LastRunningState[] newArray(int size) {
            return (new LastRunningState[size]);
        }

    }
            ;

    protected LastRunningState(android.os.Parcel in) {
        this.truckId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.stopStartTime = ((Long) in.readValue((Long.class.getClassLoader())));
        this.truckRunningState = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.lat = ((Float) in.readValue((Float.class.getClassLoader())));
        this.lng = ((Float) in.readValue((Float.class.getClassLoader())));
        this.stopNotficationSent = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public LastRunningState() {
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public Long getStopStartTime() {
        return stopStartTime;
    }

    public void setStopStartTime(Long stopStartTime) {
        this.stopStartTime = stopStartTime;
    }

    public Integer getTruckRunningState() {
        return truckRunningState;
    }

    public void setTruckRunningState(Integer truckRunningState) {
        this.truckRunningState = truckRunningState;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public Integer getStopNotficationSent() {
        return stopNotficationSent;
    }

    public void setStopNotficationSent(Integer stopNotficationSent) {
        this.stopNotficationSent = stopNotficationSent;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(truckId);
        dest.writeValue(stopStartTime);
        dest.writeValue(truckRunningState);
        dest.writeValue(lat);
        dest.writeValue(lng);
        dest.writeValue(stopNotficationSent);
    }

    public int describeContents() {
        return 0;
    }

}
