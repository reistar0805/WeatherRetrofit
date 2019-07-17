package com.example.weather;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weather.adapter.HolidayAdapter;
import com.example.weather.model.WeatherDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ArrayList<WeatherDay> listHoliday;
    private RecyclerView rvListData;
    private HolidayAdapter holidayAdapter;

    List<String> days;
    Set<String> distinctDays;
    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "211ff006de9aba9ddd122331f87cdf8b";
    public static String cnt = "7";
    public static String units = "metric";
    String cityName;
//    public static StringBuilder q = new StringBuilder("hanoi");

    private TextView txtCity, txtTemp, txtStatus, txtHumidity, txtCloud, txtWind, txtDetail, txt7day, txtDate;
    private Button btnSearch;
    private ImageView imgIcon;
    private AutoCompleteTextView atcSearch;
    List<WeatherResponse> weatherList;
    List<List<WeatherResponse>> daysList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCity = findViewById(R.id.txtCity);
        txtTemp = findViewById(R.id.txtTemp);
        txtStatus = findViewById(R.id.txtStatus);
        txtHumidity = findViewById(R.id.txtHumidity);
        txtCloud = findViewById(R.id.txtCloud);
        txtWind = findViewById(R.id.txtWind);
        atcSearch = findViewById(R.id.actSearch);
        btnSearch = findViewById(R.id.btnSearch);
        txtDetail = findViewById(R.id.txtDetail);
        rvListData = findViewById(R.id.rvListData);
        txtDate = findViewById(R.id.txtDate);
        imgIcon = findViewById(R.id.imgIcon);
        txt7day = findViewById(R.id.txt7day);
        cityName = "hanoi";



        getForecastData(cityName);
        getCurrentData(cityName);

        hideSoftKeyboard(this);

        LinearLayoutManager mLayout = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rvListData.setLayoutManager(mLayout);
        listHoliday = new ArrayList<>();
        holidayAdapter = new HolidayAdapter(MainActivity.this, listHoliday);
        rvListData.setAdapter(holidayAdapter);


        String[] countries = {"Hà Nội", "Nam Định", "Hải Dương", "Hà Nam", "Quảng Ngãi", "Thanh Hoá", "Quy Nhơn"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, countries);

        atcSearch.setAdapter(adapter);
        atcSearch.setThreshold(1);
        atcSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                hideSoftKeyboard(MainActivity.this);
//                getCurrentData(atcSearch.getText().toString());
//                getForecastData(atcSearch.getText().toString());
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCurrentData(atcSearch.getText().toString());
                getForecastData(atcSearch.getText().toString());
            }
        });


        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        String provider = LocationManager.GPS_PROVIDER;

        checkRequiredPermissions();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
        }
        locationManager.requestLocationUpdates(provider, 1000, 20, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double slat = location.getLatitude();
                double slon = location.getLongitude();


                Log.d("Demo Location ", "onLocationChanged: " + slat + " lon: " + slon);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        });

    }

    private boolean checkRequiredPermissions() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.app_name)
                    , 20000
                    , perms);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            View f = activity.getCurrentFocus();
            if (null != f && null != f.getWindowToken() && EditText.class.isAssignableFrom(f.getClass()))
                imm.hideSoftInputFromWindow(f.getWindowToken(), 0);
            else
                activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        } catch (Exception ignored) {

        }
    }

    void getCurrentData(String city) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherResponse> call = service.getCurrentWeatherData(city, AppId);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    assert weatherResponse != null;

                    //convert ngay thang
                    String day = weatherResponse.dt;
                    long lDay = Long.valueOf(day);
                    Date date = new Date(lDay * 1000L);
                    SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd/MM/yyyy HH:mm");
                    String strDay = sdf.format(date);
                    txtDate.setText(strDay);

                    //convert time binh minh
                    String sunrise = weatherResponse.sys.sunrise;
                    long lDay1 = Long.valueOf(sunrise);
                    Date date1 = new Date(lDay1 * 1000L);
                    SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
                    String strSunrise = sdf1.format(date1);

                    //convert time hoang hon
                    String sunset = weatherResponse.sys.sunset;
                    long lDay2 = Long.valueOf(sunset);
                    Date date2 = new Date(lDay2 * 1000L);
                    SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
                    String strSunset = sdf2.format(date2);

                    txtCity.setText(weatherResponse.name + "," + weatherResponse.sys.country);
                    txtTemp.setText(weatherResponse.main.temp + "°C");
                    txtHumidity.setText(weatherResponse.main.humidity + "%");
                    txtWind.setText(weatherResponse.wind.speed + "m/s");
                    txtStatus.setText(weatherResponse.weather.get(0).description);

                    //set icon
                    String icon = weatherResponse.weather.get(0).icon;
                    Glide.with(MainActivity.this)
                            .load("http://openweathermap.org/img/w/" + icon + ".png")
                            .error(R.mipmap.placeholder)
                            .into(imgIcon);

                    txtCloud.setText(weatherResponse.clouds.all + "%");

                    String stringBuilder = "Detail" +
                            "\n" + "Maxtemp: " + weatherResponse.main.temp_max + "°C" +
                            "\n" + "Mintemp: " + weatherResponse.main.temp_min + "°C" +
                            "\n" + "Pressure: " + weatherResponse.main.pressure + " hpa" +
                            "\n" + "Sunrise: " + strSunrise +
                            "\n" + "Sunset: " + strSunset;

                    txtDetail.setText(stringBuilder);


                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
                txtCity.setText(t.getMessage());

            }


        });

    }

    public static String getDate(Long milliTime) {
        Date currentDate = new Date(milliTime * 1000L);
        SimpleDateFormat df = new SimpleDateFormat("E, dd/MM");
        String date = df.format(currentDate);
        return date;
    }


    void getForecastData(String cityName) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceWeatherDay serviceDay = retrofit.create(InterfaceWeatherDay.class);
        Call<ObjWeather> call = serviceDay.fetchWeatherForecasts(units, cityName, cnt, AppId);
        call.enqueue(new Callback<ObjWeather>() {
            @Override
            public void onResponse(@NonNull Call<ObjWeather> call, @NonNull Response<ObjWeather> response) {
                if (response.code() == 200) {
                    ObjWeather forecast = response.body();
                    Log.e(TAG, "onResponse: " + forecast);
                    listHoliday.clear();
                    listHoliday.addAll(forecast.mList);
                    holidayAdapter.notifyDataSetChanged();
                    assert forecast != null;

                }

            }

            @Override
            public void onFailure(Call<ObjWeather> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

}



