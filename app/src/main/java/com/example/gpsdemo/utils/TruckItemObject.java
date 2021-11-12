package com.example.gpsdemo.utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TruckItemObject implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("companyId")
    @Expose
    private String companyId;
    @SerializedName("truckTypeId")
    @Expose
    private String truckTypeId;
    @SerializedName("truckSizeId")
    @Expose
    private String truckSizeId;
    @SerializedName("truckNumber")
    @Expose
    private String truckNumber;
    @SerializedName("transporterId")
    @Expose
    private String transporterId;
    @SerializedName("trackerType")
    @Expose
    private String trackerType;
    @SerializedName("imeiNumber")
    @Expose
    private String imeiNumber;
    @SerializedName("simNumber")
    @Expose
    private String simNumber;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("createTime")
    @Expose
    private Long createTime;
    @SerializedName("deactivated")
    @Expose
    private Boolean deactivated;
    @SerializedName("breakdown")
    @Expose
    private Boolean breakdown;
    @SerializedName("lastWaypoint")
    @Expose
    private LastWaypoint lastWaypoint;
    @SerializedName("lastRunningState")
    @Expose
    private LastRunningState lastRunningState;
    @SerializedName("durationInsideSite")
    @Expose
    private String durationInsideSite;
    @SerializedName("fuelSensorInstalled")
    @Expose
    private Boolean fuelSensorInstalled;
    @SerializedName("externalTruck")
    @Expose
    private Boolean externalTruck;
    public final static Creator<TruckItemObject> CREATOR = new Creator<TruckItemObject>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TruckItemObject createFromParcel(android.os.Parcel in) {
            return new TruckItemObject(in);
        }

        public TruckItemObject[] newArray(int size) {
            return (new TruckItemObject[size]);
        }

    };

    protected TruckItemObject(android.os.Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.companyId = ((String) in.readValue((String.class.getClassLoader())));
        this.truckTypeId = ((String) in.readValue((String.class.getClassLoader())));
        this.truckSizeId = ((String) in.readValue((String.class.getClassLoader())));
        this.truckNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.transporterId = ((String) in.readValue((String.class.getClassLoader())));
        this.trackerType = ((String) in.readValue((String.class.getClassLoader())));
        this.imeiNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.simNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.password = ((String) in.readValue((String.class.getClassLoader())));
        this.createTime = ((Long) in.readValue((Long.class.getClassLoader())));
        this.deactivated = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.breakdown = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.lastWaypoint = ((LastWaypoint) in.readValue((LastWaypoint.class.getClassLoader())));
        this.lastRunningState = ((LastRunningState) in.readValue((LastRunningState.class.getClassLoader())));
        this.durationInsideSite = ((String) in.readValue((String.class.getClassLoader())));
        this.fuelSensorInstalled = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.externalTruck = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    public TruckItemObject() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTruckTypeId() {
        return truckTypeId;
    }

    public void setTruckTypeId(String truckTypeId) {
        this.truckTypeId = truckTypeId;
    }

    public String getTruckSizeId() {
        return truckSizeId;
    }

    public void setTruckSizeId(String truckSizeId) {
        this.truckSizeId = truckSizeId;
    }

    public String getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    public String getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(String transporterId) {
        this.transporterId = transporterId;
    }

    public String getTrackerType() {
        return trackerType;
    }

    public void setTrackerType(String trackerType) {
        this.trackerType = trackerType;
    }

    public String getImeiNumber() {
        return imeiNumber;
    }

    public void setImeiNumber(String imeiNumber) {
        this.imeiNumber = imeiNumber;
    }

    public String getSimNumber() {
        return simNumber;
    }

    public void setSimNumber(String simNumber) {
        this.simNumber = simNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Boolean getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(Boolean deactivated) {
        this.deactivated = deactivated;
    }

    public Boolean getBreakdown() {
        return breakdown;
    }

    public void setBreakdown(Boolean breakdown) {
        this.breakdown = breakdown;
    }

    public LastWaypoint getLastWaypoint() {
        return lastWaypoint;
    }

    public void setLastWaypoint(LastWaypoint lastWaypoint) {
        this.lastWaypoint = lastWaypoint;
    }

    public LastRunningState getLastRunningState() {
        return lastRunningState;
    }

    public void setLastRunningState(LastRunningState lastRunningState) {
        this.lastRunningState = lastRunningState;
    }

    public String getDurationInsideSite() {
        return durationInsideSite;
    }

    public void setDurationInsideSite(String durationInsideSite) {
        this.durationInsideSite = durationInsideSite;
    }

    public Boolean getFuelSensorInstalled() {
        return fuelSensorInstalled;
    }

    public void setFuelSensorInstalled(Boolean fuelSensorInstalled) {
        this.fuelSensorInstalled = fuelSensorInstalled;
    }

    public Boolean getExternalTruck() {
        return externalTruck;
    }

    public void setExternalTruck(Boolean externalTruck) {
        this.externalTruck = externalTruck;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(companyId);
        dest.writeValue(truckTypeId);
        dest.writeValue(truckSizeId);
        dest.writeValue(truckNumber);
        dest.writeValue(transporterId);
        dest.writeValue(trackerType);
        dest.writeValue(imeiNumber);
        dest.writeValue(simNumber);
        dest.writeValue(name);
        dest.writeValue(password);
        dest.writeValue(createTime);
        dest.writeValue(deactivated);
        dest.writeValue(breakdown);
        dest.writeValue(lastWaypoint);
        dest.writeValue(lastRunningState);
        dest.writeValue(durationInsideSite);
        dest.writeValue(fuelSensorInstalled);
        dest.writeValue(externalTruck);
    }

    public int describeContents() {
        return 0;
    }
}