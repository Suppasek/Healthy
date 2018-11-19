package com.example.suppasek.healthy.Sleep;

import android.os.Parcel;
import android.os.Parcelable;

public class Sleep implements Parcelable {

    private String id;
    private String date;
    private String sleepTime;
    private String wakeTime;
    private String totalSleepTime;

    public Sleep(String id, String date, String sleepTime, String wakeTime, String totalSleepTime) {
        this.id = id;
        this.date = date;
        this.sleepTime = sleepTime;
        this.wakeTime = wakeTime;
        this.totalSleepTime = totalSleepTime;
    }

    public Sleep(String date, String sleepTime, String wakeTime, String totalSleepTime) {
        this.date = date;
        this.sleepTime = sleepTime;
        this.wakeTime = wakeTime;
        this.totalSleepTime = totalSleepTime;
    }

    protected Sleep(Parcel in) {
        id = in.readString();
        date = in.readString();
        sleepTime = in.readString();
        wakeTime = in.readString();
        totalSleepTime = in.readString();
    }

    public static final Creator<Sleep> CREATOR = new Creator<Sleep>() {
        @Override
        public Sleep createFromParcel(Parcel in) {
            return new Sleep(in);
        }

        @Override
        public Sleep[] newArray(int size) {
            return new Sleep[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getWakeTime() {
        return wakeTime;
    }

    public void setWakeTime(String wakeTime) {
        this.wakeTime = wakeTime;
    }

    public String getTotalSleepTime() {
        return totalSleepTime;
    }

    public void setTotalSleepTime(String totalSleepTime) {
        this.totalSleepTime = totalSleepTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(date);
        dest.writeString(sleepTime);
        dest.writeString(wakeTime);
        dest.writeString(totalSleepTime);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
