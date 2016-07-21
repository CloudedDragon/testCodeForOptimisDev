package com.optimum.optimumme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.marcohc.robotocalendar.RobotoCalendarView;
import com.optimum.optimumme.R;
import com.optimum.optimumme.adapter.QuickCommitAdapter;
import com.optimum.optimumme.application.MainApplication;
import com.optimum.optimumme.http.HttpManager;
import com.optimum.optimumme.http.gsonclass.GsonCommitContainer;
import com.optimum.optimumme.http.listener.GetCommitContainerListListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Bruce_Lan on 2016/7/22.
 */
public class CalendarActicity extends AppCompatActivity implements RobotoCalendarView.RobotoCalendarListener {
    private final static String TAG = "CalendarActicity";

    private AppCompatActivity appCompatActivity;
    private TextView titleTV;
    private RobotoCalendarView robotoCalendarView;
    private int currentMonthIndex;
    private Calendar currentCalendar;
    private Locale locale;
    private String fullName = "";
    private List<GsonCommitContainer> gsonList;
    private List<GsonCommitContainer> showList;
    private Date selectedDate;
    private RecyclerView mRecyclerView;
    private QuickCommitAdapter quickCommitAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Intent intent = this.getIntent();
        fullName = intent.getStringExtra("fullname");
        Log.d(TAG, "onCreate fullName: " + fullName);
        appCompatActivity = this;
        titleTV = (TextView) findViewById(R.id.title_tv);
        titleTV.setText(fullName);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        showList = new ArrayList<>();
        updateAdapter();
        // Gets the calendar from the view
        robotoCalendarView = (RobotoCalendarView) findViewById(R.id.robotoCalendarPicker);
        // Set listener, in this case, the same activity
        robotoCalendarView.setRobotoCalendarListener(this);

        // Initialize the RobotoCalendarPicker with the current index and date
        currentMonthIndex = 0;
        locale = appCompatActivity.getResources().getConfiguration().locale;
        currentCalendar = Calendar.getInstance(Locale.ENGLISH);

        // Mark current day
//        robotoCalendarView.markDayAsCurrentDay(currentCalendar.getTime());


        HttpManager httpManager = new HttpManager();
        httpManager.init(MainApplication.getInstance().getApplicationContext());

        httpManager.getCommitContainerList(appCompatActivity, fullName, new GetCommitContainerListListener() {
            @Override
            public void onResult(GsonCommitContainer[] list) {
                Log.d(TAG, "getCommitContainerList response :" + list.toString());
                gsonList = Arrays.asList(list);
                for (int i = 0; i < gsonList.size(); i++) {
                    Log.d(TAG, "gsonList.get(i) :" + gsonList.get(i).toString());
                    Log.d(TAG, "gsonList.get(i) getCommitDate:" + gsonList.get(i).getCommitDate(locale));
                    Calendar commitCal = Calendar.getInstance();
                    commitCal.setTime(gsonList.get(i).getCommitDate(locale));
                    if(currentCalendar.get(Calendar.YEAR) == commitCal.get(Calendar.YEAR) && currentCalendar.get(Calendar.MONTH) == commitCal.get(Calendar.MONTH)){
                        robotoCalendarView.markFirstUnderlineWithStyle(RobotoCalendarView.RED_COLOR, gsonList.get(i).getCommitDate(locale));
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


//        robotoCalendarView.markDayAsSelectedDay(currentCalendar.getTime());
//        robotoCalendarView.markFirstUnderlineWithStyle(RobotoCalendarView.BLUE_COLOR, todayCalendar.getTime());
    }

    private void updateAdapter() {

        if(showList!=null && showList.size()>0){
            quickCommitAdapter = new QuickCommitAdapter(this,showList);
            quickCommitAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
            mRecyclerView.setAdapter(quickCommitAdapter);
        }
        else{
            // set emptyview
        }


    }

    @Override
    public void onDateSelected(Date date) {
        robotoCalendarView.markDayAsSelectedDay(date);
        selectedDate = date;
        showList.clear();

        for (int i = 0; i < gsonList.size(); i++) {
            Log.d(TAG, "gsonList.get(i) :" + gsonList.get(i).toString());
            Log.d(TAG, "gsonList.get(i) getCommitDate:" + gsonList.get(i).getCommitDate(locale));
            Calendar commitCal = Calendar.getInstance();
            commitCal.setTime(gsonList.get(i).getCommitDate(locale));

            Calendar selectedCal = Calendar.getInstance();
            selectedCal.setTime(selectedDate);
            if(selectedCal.get(Calendar.YEAR) == commitCal.get(Calendar.YEAR) && selectedCal.get(Calendar.MONTH) == commitCal.get(Calendar.MONTH ) &&
                    selectedCal.get(Calendar.DAY_OF_MONTH) == commitCal.get(Calendar.DAY_OF_MONTH)
                    ){
                showList.add(gsonList.get(i));
            }
        }

        updateAdapter();

    }

    @Override
    public void onRightButtonClick() {
        currentMonthIndex++;
        updateCalendar();
    }

    @Override
    public void onLeftButtonClick() {
        currentMonthIndex--;
        updateCalendar();
    }

    private void updateCalendar() {
        currentCalendar = Calendar.getInstance(Locale.ENGLISH);
        currentCalendar.add(Calendar.MONTH, currentMonthIndex);
        robotoCalendarView.initializeCalendar(currentCalendar);
        for (int i = 0; i < gsonList.size(); i++) {
            Log.d(TAG, "gsonList.get(i) :" + gsonList.get(i).toString());
            Calendar commitCal = Calendar.getInstance();
            commitCal.setTime(gsonList.get(i).getCommitDate(locale));
            if(currentCalendar.get(Calendar.YEAR) == commitCal.get(Calendar.YEAR) && currentCalendar.get(Calendar.MONTH) == commitCal.get(Calendar.MONTH)){
                robotoCalendarView.markFirstUnderlineWithStyle(RobotoCalendarView.RED_COLOR, gsonList.get(i).getCommitDate(locale));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
