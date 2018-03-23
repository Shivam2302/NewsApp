package com.example.shivam.practice;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shivam on 21/1/18.
 */

public class WeatherFragment extends android.support.v4.app.Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<ArrayList<Weather>> {

    private static final String URL =
            "http://samples.openweathermap.org/data/2.5/forecast/daily?id=524901&appid=de93fd58072cb84e92203f9cdd2aea65";
    View view;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // setting title of tool bar ...
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Weather");

        view = inflater.inflate(R.layout.weather_fragment,container,false);

        getLoaderManager().initLoader(0, null, this);

        return view;
    }


    @Override
    public Loader<ArrayList<Weather>> onCreateLoader(int id, Bundle args) {
        return new WeatherLoader(getContext(),URL);
    }


    @Override
    public void onLoadFinished(Loader<ArrayList<Weather>> loader, ArrayList<Weather> data) {

        LinearLayout main = (LinearLayout) view.findViewById(R.id.main_layout);

        // setting city name ...
        TextView city = (TextView) view.findViewById(R.id.city);
        city.setText(data.get(0).getCity());

        // calculating ate from unix time ...
        TextView date = (TextView) view.findViewById(R.id.date);
        date.setText(Long.toString(data.get(0).getDate()));
        

        TextView mainTemperature = (TextView) view.findViewById(R.id.main_temperature);
        mainTemperature.setText(Double.toString(data.get(0).getAverageTemperature()-273.15));

        TextView description = (TextView) view.findViewById(R.id.description);
        description.setText(data.get(0).getDescription());

        String min = Double.toString(Math.floor((data.get(0).getMinTemperature()-273.15)*10)/10);
        String max = Double.toString(Math.floor((data.get(0).getMaxTemperature()-273.15)*10)/10);

        String minM = min +" \u00b0"+"C / "+max+ " \u00b0"+"C ";

        String min1 = "min_max";

        TextView minMax = (TextView) view.findViewById(R.id.min_max);
        minMax.setText(minM);


        LinearLayout childView1, childView2,childView3,childView4,childView5,c,c1,c3;
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        childView1 = (LinearLayout) inflater.inflate(R.layout.weather_row, null);
        childView2 = (LinearLayout) inflater.inflate(R.layout.weather_row, null);
        childView3 = (LinearLayout) inflater.inflate(R.layout.weather_row, null);
        childView4 = (LinearLayout) inflater.inflate(R.layout.weather_row, null);
        childView5 = (LinearLayout) inflater.inflate(R.layout.weather_row, null);
        c = (LinearLayout) inflater.inflate(R.layout.weather_row, null);
        c1 = (LinearLayout) inflater.inflate(R.layout.weather_row, null);
        c3 = (LinearLayout) inflater.inflate(R.layout.weather_row, null);




        main.addView(childView1);
        main.addView(childView2);
        main.addView(childView3);
        main.addView(childView4);
        main.addView(childView5);

        main.addView(c);
        main.addView(c1);
        main.addView(c3);

    }

    private void getSystemService(String layoutInflaterService) {
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Weather>> loader) {


    }
}
