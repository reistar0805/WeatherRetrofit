package com.example.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weather.MainActivity;
import com.example.weather.R;
import com.example.weather.model.WeatherDay;

import java.util.ArrayList;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.MyViewHolder> {
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgIconDay;
        public TextView tvDay;
        public TextView tvMax;
        public TextView tvMin;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvMax = itemView.findViewById(R.id.tvMax);
            tvMin = itemView.findViewById(R.id.tvMin);
            imgIconDay = itemView.findViewById(R.id.imgIconDay);
        }
    }

    private ArrayList<WeatherDay> mListHoliday;
    private Context mContext;

    public HolidayAdapter(Context mContext, ArrayList<WeatherDay> listHoliday) {
        this.mListHoliday = listHoliday;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View iView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false);
        mContext = parent.getContext();

        return new MyViewHolder(iView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        WeatherDay objWeather = mListHoliday.get(position);

        String date = MainActivity.getDate(Long.parseLong(objWeather.getDt()));
        holder.tvDay.setText(date);
        holder.tvMax.setText(objWeather.getTemp().getMax());
        holder.tvMin.setText(objWeather.getTemp().getMin());

        String icon = objWeather.getLisWeather().get(0).getIcon();
        Glide.with(mContext)
                .load("http://openweathermap.org/img/w/" + icon + ".png")
                .error(R.mipmap.thermometer)
                .into(holder.imgIconDay);


    }

    @Override
    public int getItemCount() {
        return mListHoliday.size();
    }
}
