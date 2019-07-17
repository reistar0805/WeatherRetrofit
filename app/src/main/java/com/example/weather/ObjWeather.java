package com.example.weather;

import com.example.weather.model.WeatherDay;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ObjWeather {
    @SerializedName("cod")
    public String cod;
    @SerializedName("message")
    public String message;
    @SerializedName("cnt")
    public String cnt;
    @SerializedName("list")
    public List<WeatherDay> mList;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public List<WeatherDay> getmList() {
        return mList;
    }

    public void setmList(List<WeatherDay> mList) {
        this.mList = mList;
    }
}
