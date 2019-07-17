package com.example.weather.model;

public class Holiday {
    String tvDay;
    String urlIconDay;
    String tvMax;
    String tvMin;

    public Holiday() {
    }

    public Holiday(String tvDay, String urlIconDay, String tvMax, String tvMin) {
        this.tvDay = tvDay;
        this.urlIconDay = urlIconDay;
        this.tvMax = tvMax;
        this.tvMin = tvMin;
    }

    public String getTvDay() {
        return tvDay;
    }

    public void setTvDay(String tvDay) {
        this.tvDay = tvDay;
    }

    public String getUrlIconDay() {
        return urlIconDay;
    }

    public void setUrlIconDay(String urlIconDay) {
        this.urlIconDay = urlIconDay;
    }

    public String getTvMax() {
        return tvMax;
    }

    public void setTvMax(String tvMax) {
        this.tvMax = tvMax;
    }

    public String getTvMin() {
        return tvMin;
    }

    public void setTvMin(String tvMin) {
        this.tvMin = tvMin;
    }
}
