package com.example.gpsdemo.utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiveVehicleItemObject implements Parcelable
{

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Designation")
    @Expose
    private String designation;
    @SerializedName("ContactNo")
    @Expose
    private String contactNo;
    @SerializedName("wardId")
    @Expose
    private String wardId;
    @SerializedName("SectorId")
    @Expose
    private String sectorId;
    @SerializedName("circleId")
    @Expose
    private String circleId;
    @SerializedName("EmpCode")
    @Expose
    private String empCode;
    @SerializedName("VehicleNo")
    @Expose
    private String vehicleNo;
    @SerializedName("VehicleType")
    @Expose
    private String vehicleType;
    @SerializedName("Lat")
    @Expose
    private String lat;
    @SerializedName("Lng")
    @Expose
    private String lng;
    @SerializedName("Speed")
    @Expose
    private String speed;
    @SerializedName("Ignition")
    @Expose
    private String ignition;
    @SerializedName("DatedOn")
    @Expose
    private String datedOn;
    @SerializedName("VehicleImei")
    @Expose
    private String vehicleImei;
    public final static Parcelable.Creator<LiveVehicleItemObject> CREATOR = new Creator<LiveVehicleItemObject>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LiveVehicleItemObject createFromParcel(Parcel in) {
            return new LiveVehicleItemObject(in);
        }

        public LiveVehicleItemObject[] newArray(int size) {
            return (new LiveVehicleItemObject[size]);
        }

    }
            ;

    protected LiveVehicleItemObject(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.designation = ((String) in.readValue((String.class.getClassLoader())));
        this.contactNo = ((String) in.readValue((String.class.getClassLoader())));
        this.wardId = ((String) in.readValue((String.class.getClassLoader())));
        this.sectorId = ((String) in.readValue((String.class.getClassLoader())));
        this.circleId = ((String) in.readValue((String.class.getClassLoader())));
        this.empCode = ((String) in.readValue((String.class.getClassLoader())));
        this.vehicleNo = ((String) in.readValue((String.class.getClassLoader())));
        this.vehicleType = ((String) in.readValue((String.class.getClassLoader())));
        this.lat = ((String) in.readValue((String.class.getClassLoader())));
        this.lng = ((String) in.readValue((String.class.getClassLoader())));
        this.speed = ((String) in.readValue((String.class.getClassLoader())));
        this.ignition = ((String) in.readValue((String.class.getClassLoader())));
        this.datedOn = ((String) in.readValue((String.class.getClassLoader())));
        this.vehicleImei = ((String) in.readValue((String.class.getClassLoader())));
    }

    public LiveVehicleItemObject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public String getSectorId() {
        return sectorId;
    }

    public void setSectorId(String sectorId) {
        this.sectorId = sectorId;
    }

    public String getCircleId() {
        return circleId;
    }

    public void setCircleId(String circleId) {
        this.circleId = circleId;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
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

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getIgnition() {
        return ignition;
    }

    public void setIgnition(String ignition) {
        this.ignition = ignition;
    }

    public String getDatedOn() {
        return datedOn;
    }

    public void setDatedOn(String datedOn) {
        this.datedOn = datedOn;
    }

    public String getVehicleImei() {
        return vehicleImei;
    }

    public void setVehicleImei(String vehicleImei) {
        this.vehicleImei = vehicleImei;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(designation);
        dest.writeValue(contactNo);
        dest.writeValue(wardId);
        dest.writeValue(sectorId);
        dest.writeValue(circleId);
        dest.writeValue(empCode);
        dest.writeValue(vehicleNo);
        dest.writeValue(vehicleType);
        dest.writeValue(lat);
        dest.writeValue(lng);
        dest.writeValue(speed);
        dest.writeValue(ignition);
        dest.writeValue(datedOn);
        dest.writeValue(vehicleImei);
    }

    public int describeContents() {
        return 0;
    }

}