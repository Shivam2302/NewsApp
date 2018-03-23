package com.example.shivam.practice;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by shivam on 18/1/18.
 */


public class NewsItem {


    private String heading, description, date, time, urlForImage, urlForDetails;



    public NewsItem(String heading, String description, String Date, String Time, String urlForImage, String urlForDetails) {
        this.heading = heading;
        this.description = description;
        this.time = Time;
        this.urlForImage = urlForImage;
        this.urlForDetails = urlForDetails;

        // changing the format of the date that is to saved...
        set(Date);
    }



    public void set(String Date){

        // formatting date to print name of the month ...
        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MMMM yyyy");

        try {
            date = myFormat.format(fromUser.parse(Date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    public String getDate() { return date;}


    public String getTime() { return time;}


    public String getUrlForImage() { return urlForImage;}


    public String getUrlForDetails() { return urlForDetails; }


    public String getHeading() {
        return heading;
    }


    public String getDescription() {
        return description;
    }


}
