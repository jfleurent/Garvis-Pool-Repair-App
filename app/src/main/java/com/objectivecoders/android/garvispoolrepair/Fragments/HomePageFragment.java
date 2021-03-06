package com.objectivecoders.android.garvispoolrepair.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Spinner;

import com.objectivecoders.android.garvispoolrepair.AuxiliaryFragmentHolderActivity;
import com.objectivecoders.android.garvispoolrepair.CreateWorkOrderActivity;
import com.objectivecoders.android.garvispoolrepair.DataObjects.WorkOrderDate;
import com.objectivecoders.android.garvispoolrepair.R;

import java.sql.Date;

/**
 * Created by jeffr on 3/3/2018.
 */

public class HomePageFragment extends android.support.v4.app.Fragment {

    CalendarView calendarView;

    public HomePageFragment() {
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_page, container, false);

        calendarView = rootView.findViewById(R.id.calendarView);

        calendarView.setDate(System.currentTimeMillis());


        if(getArguments() != null && getArguments().getString("ToShow").equals("Date")){

            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                    String month = i1+1 > 10 ? String.valueOf(i1+1) : "0"+ String.valueOf(i1+1);
                    String day = i2 > 10 ? String.valueOf(i2) : "0"+ String.valueOf(i2);
                        CreateWorkOrderActivity.date  = month+"-"+day+"-"+String.valueOf(i);



                }

            });

        }
        else{
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                    Intent workOrdersIntent = new Intent(getActivity(), AuxiliaryFragmentHolderActivity.class);
                    workOrdersIntent.putExtra("ToShow","WorkOrderOfTheDay");
                    startActivity(workOrdersIntent);
                }
            });

        }


        return rootView;
    }

    //Accounts for bundle in CreateWorkOrderActivity having data in it
    @Override
    public void onResume() {
        CreateWorkOrderActivity.getBundle().clear();
        super.onResume();
    }
}
