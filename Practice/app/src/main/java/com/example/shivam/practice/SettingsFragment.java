package com.example.shivam.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shivam on 21/1/18.
 */

public class SettingsFragment extends android.support.v4.app.Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // setting title of tool bar ...
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Settings");

        return inflater.inflate(R.layout.setting_fragment,container,false);
    }
}
