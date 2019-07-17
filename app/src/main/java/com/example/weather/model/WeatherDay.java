package com.example.weather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherDay {
    @SerializedName("dt")
    String dt;
    @SerializedName("speed")
    String speed;
    @SerializedName("deg")
    String deg;
    @SerializedName("clouds")
    String clouds;
    @SerializedName("temp")
    Templ temp;
    @SerializedName("weather")
    List<Weather> lisWeather;

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public Templ getTemp() {
        return temp;
    }

    public void setTemp(Templ temp) {
        this.temp = temp;
    }

    public List<Weather> getLisWeather() {
        return lisWeather;
    }

    public void setLisWeather(List<Weather> lisWeather) {
        this.lisWeather = lisWeather;
    }
}
