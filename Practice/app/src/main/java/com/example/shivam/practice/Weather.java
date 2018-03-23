package com.example.shivam.practice;

/**
 * Created by shivam on 28/2/18.
 */

public class Weather {

    private long date;
    private String city;
    private String description;

    private double averageTemperature;
    private double minTemperature;
    private double maxTemperature;
    private double pressure;

    public Weather(String city, String description, long date, double averageTemperature, double minTemperature, double maxTemperature, double pressure) {
        this.date = date;
        this.city = city;
        this.description = description;
        this.averageTemperature = averageTemperature;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.pressure = pressure;
    }

    public long getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getPressure() { return pressure; }


}
