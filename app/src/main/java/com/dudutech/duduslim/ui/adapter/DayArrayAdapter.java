package com.dudutech.duduslim.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dudutech.duduslim.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import antistatic.spinnerwheel.adapters.AbstractWheelTextAdapter;

/**
 * Created by winbin on 2014/4/24.
 */
public class DayArrayAdapter extends AbstractWheelTextAdapter {

    // Count of days to be shown
    private final int daysCount = 4;

    // Calendar
    Calendar calendar;

    /**
     * Constructor
     */
    public  DayArrayAdapter(Context context, Calendar calendar) {
        super(context, R.layout.time_picker_custom_day, NO_RESOURCE);
        this.calendar = calendar;

        setItemTextResource(R.id.time2_monthday);
    }
    public int getToday() {
        return daysCount / 2;
    }

    @Override
    public View getItem(int index, View cachedView, ViewGroup parent) {
        int day = -daysCount/2 + index;
        Calendar newCalendar = (Calendar) calendar.clone();
        newCalendar.roll(Calendar.DAY_OF_YEAR, day);

        View view = super.getItem(index, cachedView, parent);

        TextView weekday = (TextView) view.findViewById(R.id.time2_weekday);
        if (day == 0) {
            weekday.setText("");
        } else {
            DateFormat format = new SimpleDateFormat("EEE");
            weekday.setText(format.format(newCalendar.getTime()));
        }

        TextView monthday = (TextView) view.findViewById(R.id.time2_monthday);
        if (day == 0) {
            monthday.setText("今天");
            monthday.setTextColor(0xFF0000F0);
        } else {
            DateFormat format = new SimpleDateFormat("MMM d");

            monthday.setText(format.format(newCalendar.getTime()));
            monthday.setTextColor(0xFF111111);
        }
        DateFormat dFormat = new SimpleDateFormat("MMM d");
        view.setTag(dFormat.format(newCalendar.getTime()));
        return view;
    }

    @Override
    public int getItemsCount() {
        return daysCount + 1;
    }

    @Override
    protected CharSequence getItemText(int index) {
        return "";
    }

}
