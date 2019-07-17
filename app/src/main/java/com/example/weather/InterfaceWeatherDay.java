package com.example.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceWeatherDay {

    @GET("data/2.5/forecast/daily?")
    Call<ObjWeather> fetchWeatherForecasts(
            @Query("units") String metric,
            @Query("q") String cityName,
            @Query("cnt") String cnt,
            @Query("appid") String app_id);
}
