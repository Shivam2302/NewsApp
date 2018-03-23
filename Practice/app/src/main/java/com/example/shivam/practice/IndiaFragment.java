package com.example.shivam.practice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * Created by shivam on 20/1/18.
 */

public class IndiaFragment extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<ArrayList<NewsItem>> {


    private static final String URL =
            "https://api.cognitive.microsoft.com/bing/v7.0/news/?Category=india&count=50&subscription-key=6010e04d87f04a7fb8bb46dd370d8713";
    private View view;
    private ProgressBar spinner;
    private ImageView image;
    private RecyclerView recyclerView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.common_headline_fragment,container,false);

        image = (ImageView) view.findViewById(R.id.no_news);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        spinner = (ProgressBar) view.findViewById(R.id.progressBar1);

        // check for the internet connection ...
        if(!checkForConnection(getContext())){

            recyclerView.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
            image.setImageResource(R.drawable.no_internet);
        }
        else{

            image.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            // creating loader manager ...
            getLoaderManager().initLoader(0, null, this);
        }

        return view;
    }




    /**
     * method for checking internet connection ...
     */
    private boolean checkForConnection(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        }
        else
            return false;

    }




    @Override
    public android.support.v4.content.Loader<ArrayList<NewsItem>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(getContext(),URL);
    }




    /**
     * This is the call back method of loader which is called on load finised ...
     */
    @Override
    public void onLoadFinished(android.support.v4.content.Loader<ArrayList<NewsItem>> loader, ArrayList<NewsItem> data) {

        spinner.setVisibility(View.GONE);
        if(data!=null) {

            image.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            RecyclerAdapter adapter = new RecyclerAdapter(data, getContext());
            recyclerView.setAdapter(adapter);
        }
        else{

            recyclerView.setVisibility(View.GONE);
            image.setVisibility(View.VISIBLE);
        }
    }




    @Override
    public void onLoaderReset(android.support.v4.content.Loader<ArrayList<NewsItem>> loader) {

    }
}
