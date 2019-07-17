package com.example.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("data/2.5/weather?units=metric")
    Call<WeatherResponse> getCurrentWeatherData(
            @Query("q") String city,
//            @Query("lon") String lon,
//            @Query("lat") String lat,
            @Query("appid") String app_id);

}
