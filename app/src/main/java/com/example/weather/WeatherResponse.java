package com.example.weather;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherResponse {
    @SerializedName("coord")
    public Coord coord;
    @SerializedName("sys")
    public Sys sys;
    @SerializedName("weather")
    public ArrayList<Weather> weather = new ArrayList<Weather>();
    @SerializedName("main")
    public Main main;
    @SerializedName("wind")
    public Wind wind;
    @SerializedName("rain")
    public Rain rain;
    @SerializedName("clouds")
    public Clouds clouds;
    @SerializedName("dt")
    public String dt;
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("cod")
    public String cod;

    public WeatherResponse() {
    }

    public WeatherResponse(Coord coord, Sys sys, ArrayList<Weather> weather, Main main, Wind wind, Rain rain,
                           Clouds clouds, String dt, String id, String name, String cod) {
        this.coord = coord;
        this.sys = sys;
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.rain = rain;
        this.clouds = clouds;
        this.dt = dt;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
}


class Weather {
    @SerializedName("id")
    public String id;
    @SerializedName("main")
    public String main;
    @SerializedName("description")
    public String description;
    @SerializedName("icon")
    public String icon;

    public Weather() {
    }

    public Weather(String id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

class Clouds {
    @SerializedName("all")
    public String all;
}

class Rain {
    @SerializedName("3h")
    public String h3;
}

class Wind {
    @SerializedName("speed")
    public String speed;
    @SerializedName("deg")
    public String deg;
}

class Main {
    @SerializedName("temp")
    public String temp;
    @SerializedName("humidity")
    public String humidity;
    @SerializedName("pressure")
    public String pressure;
    @SerializedName("temp_min")
    public String temp_min;
    @SerializedName("temp_max")
    public String temp_max;

    public Main() {
    }

    public Main(String temp, String humidity, String pressure, String temp_min, String temp_max) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }
}

class Sys {
    @SerializedName("country")
    public String country;
    @SerializedName("sunrise")
    public String sunrise;
    @SerializedName("sunset")
    public String sunset;
}

class Coord {
    @SerializedName("lon")
    public String lon;
    @SerializedName("lat")
    public String lat;
}
class Forecast{
    @SerializedName("cod")
    public String cod;
    @SerializedName("message")
    public float message;
    @SerializedName("cnt")
    public int cnt;
    @SerializedName("list")
    public ArrayList<WeatherResponse> dayOfTheWeek = new ArrayList<WeatherResponse>();
    @SerializedName("city")
    public String city;

    public Forecast() {
    }

    public Forecast(String cod, float message, int cnt, ArrayList<WeatherResponse> dayOfTheWeek, String city) {
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.dayOfTheWeek = dayOfTheWeek;
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public float getMessage() {
        return message;
    }

    public void setMessage(float message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public ArrayList<WeatherResponse> getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(ArrayList<WeatherResponse> dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

