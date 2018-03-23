package com.example.shivam.practice;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by shivam on 22/1/18.
 */

public class NewsLoader extends android.support.v4.content.AsyncTaskLoader<ArrayList<NewsItem>>{


    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private String Url;


    public NewsLoader(Context context, String Url) {
        super(context);
        this.Url = Url;
    }



    /**
     * This method is first method which is run when the loader is created..
     * @return ArrayList of news items
     */
    @Override
    public ArrayList<NewsItem> loadInBackground() {
        URL url = createUrl(Url);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = "";

        try {
            jsonResponse = makeHttpRequest(url);
        } catch (Exception e) {
            Log.e("ERROR", e.toString());

        }

        return extractFeatureFromJson(jsonResponse);
    }



    @Override
    protected void onStartLoading() {
        forceLoad();
    }




    /**
     * This method is used to convert string into url object ...
     * @param stringUrl url for which object is to be formed ...
     * @return url object ...
     */
    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }



    /**
     * @param url is the url object with which http connection is to be set up ...
     * @return string having json response fetched from the url ...
     * @throws IOException
     */
    private String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }

            else{
                Log.e("TAG","Response code from server is not 200");
            }

        }

        catch (Exception e) {
            Log.e("TAG","Error in making Http Connection using given URL");

        }

        finally {

            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }

        return jsonResponse;
    }




    /**
     * This method converts input stream into string ...
     * @param inputStream is the stream of bits fetched from server ...
     * @return string
     * @throws IOException
     */
    private String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();

        if (inputStream != null) {

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }

        }
        return output.toString();
    }




    /**
     * This method is used to extract features from the Json ...
     * @param newsJSON is the string of Json data ...
     * @return ArrayList of news items where each news item have many different fields ...
     */
    private ArrayList<NewsItem> extractFeatureFromJson(String newsJSON) {

        String urlForImage;

        ArrayList<NewsItem> news = new ArrayList<>();

        try {

            // return null if the json response is empty ...
            if(!newsJSON.equals("")) {

                JSONObject baseJsonResponse = new JSONObject(newsJSON);
                JSONArray valuesArray = baseJsonResponse.getJSONArray("value");

                // If there are results in the features array
                if (valuesArray.length() > 0) {

                    for (int i = 0; i < valuesArray.length(); i++) {

                        // Extract out the first news value
                        JSONObject firstValue = valuesArray.getJSONObject(i);

                        // Extract out the title, time, and tsunami values
                        String title = firstValue.getString("name");
                        String description = firstValue.getString("description");
                        String date = getDate(firstValue.getString("datePublished"));
                        String time = getTime(firstValue.getString("datePublished"));
                        String urlForDetails = firstValue.getString("url");

                        // if image not present ...
                        if(firstValue.has("image")){

                            JSONObject image = firstValue.getJSONObject("image");
                            JSONObject thumbnail = image.getJSONObject("thumbnail");
                            urlForImage = thumbnail.getString("contentUrl");
                        }

                        else
                            urlForImage = "";

                        news.add(new NewsItem(title, description, date, time, urlForImage, urlForDetails));
                    }

                    return news;
                }
            }
        }

        catch (JSONException e) {
            urlForImage = " ";
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }

        return null;
    }




    /**
     * this method is used to convert string datePublished into date ...
     */
    private String getDate(String datePublished) {

        datePublished = datePublished.substring(0,datePublished.indexOf("T"));
        return datePublished;
    }




    /**
     *  this method is used to split string datePublished into time ...
     */
    private String getTime(String datePublished){

        datePublished = datePublished.substring(datePublished.indexOf("T")+1,datePublished.indexOf("."));
        return datePublished;


    }
}
