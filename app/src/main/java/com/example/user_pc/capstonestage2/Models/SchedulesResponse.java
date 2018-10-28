package com.example.user_pc.capstonestage2.Models;

import com.example.user_pc.capstonestage2.Models.Schedule;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SchedulesResponse {

    @SerializedName("request_hash")
    private String requestHash;
    @SerializedName("monday")
    private List<Schedule> monday;
    @SerializedName("tuesday")
    private List<Schedule> tuesday;
    @SerializedName("wednesday")
    private List<Schedule> wednesday;
    @SerializedName("thursday")
    private List<Schedule> thursday;
    @SerializedName("friday")
    private List<Schedule> friday;
    @SerializedName("saturday")
    private List<Schedule> saturday;
    @SerializedName("sunday")
    private List<Schedule> sunday;

    public String getRequestHash() {
        return requestHash;
    }

    public void setRequestHash(String requestHash) {
        this.requestHash = requestHash;
    }

    public List<Schedule> getMonday() {
        return monday;
    }

    public void setMonday(List<Schedule> monday) {
        this.monday = monday;
    }

    public List<Schedule> getTuesday() {
        return tuesday;
    }

    public void setTuesday(List<Schedule> tuesday) {
        this.tuesday = tuesday;
    }

    public List<Schedule> getWednesday() {
        return wednesday;
    }

    public void setWednesday(List<Schedule> wednesday) {
        this.wednesday = wednesday;
    }

    public List<Schedule> getThursday() {
        return thursday;
    }

    public void setThursday(List<Schedule> thursday) {
        this.thursday = thursday;
    }

    public List<Schedule> getFriday() {
        return friday;
    }

    public void setFriday(List<Schedule> friday) {
        this.friday = friday;
    }

    public List<Schedule> getSaturday() {
        return saturday;
    }

    public void setSaturday(List<Schedule> saturday) {
        this.saturday = saturday;
    }

    public List<Schedule> getSunday() {
        return sunday;
    }

    public void setSunday(List<Schedule> friday) {
        this.sunday = sunday;
    }

}
