package com.example.weather.model;

import com.google.gson.annotations.SerializedName;

public class Templ {
    @SerializedName("day")
    String dt;
    @SerializedName("min")
    String min;
    @SerializedName("max")
    String max;
    @SerializedName("night")
    String night;
    @SerializedName("eve")
    String eve;
    @SerializedName("morn")
    String morn;

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getEve() {
        return eve;
    }

    public void setEve(String eve) {
        this.eve = eve;
    }

    public String getMorn() {
        return morn;
    }

    public void setMorn(String morn) {
        this.morn = morn;
    }
}
