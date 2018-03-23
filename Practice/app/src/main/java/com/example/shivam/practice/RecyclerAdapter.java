package com.example.shivam.practice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by shivam on 18/1/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    ArrayList<NewsItem> list;
    Context context;


    public RecyclerAdapter(ArrayList<NewsItem> list, Context context) {
        this.list = list;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);

        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        NewsItem item = list.get(position);

        //setting heading ...
        holder.heading.setText(item.getHeading());

        // setting date ...
        holder.date.setText(item.getDate());

        // if picasso is not able to fetch image, Set no image to the image view ...
        try{
            Picasso.with(context).load(item.getUrlForImage()).into(holder.image);
        }

        catch(Exception E){
            // set image of no image to the item ...
            holder.image.setImageResource(R.drawable.no_image);
        }

    }



    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView heading,date,time;
        public ImageView image;


        public ViewHolder(View itemView) {
            super(itemView);

            heading = (TextView) itemView.findViewById(R.id.heading);

            date = (TextView) itemView.findViewById(R.id.date);

            image = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
