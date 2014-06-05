package com.dudutech.duduslim.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.TextView;

import com.dudutech.duduslim.R;
import com.dudutech.duduslim.entity.WeightRecord;
import com.dudutech.duduslim.service.WeightRecordService;
import com.dudutech.duduslim.service.WeightRecordServiceImp;
import com.dudutech.duduslim.ui.adapter.DayArrayAdapter;

import java.util.Calendar;

import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.adapters.ArrayWheelAdapter;
import antistatic.spinnerwheel.adapters.NumericWheelAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by winbin on 2014/5/10.
 */
public class AddWeightDialog extends Dialog {


    @InjectView(R.id.wheel_weight1)
    AbstractWheel wheel_weight1;
    @InjectView(R.id.wheel_weight2)
    AbstractWheel wheel_weight2;
    @InjectView(R.id.wheel_status)
    AbstractWheel wheel_status;
    @InjectView(R.id.wheel_day)
    AbstractWheel wheel_day;

    @InjectView(R.id.tv_cancel)
    TextView tv_cancel;
    @InjectView(R.id.tv_save)
    TextView tv_save;

    private WeightRecordService mService;
    private Calendar calendarNow;

    private String[] mStates;

    public AddWeightDialog(Context context) {
        super(context);
    }

    public AddWeightDialog(Context context, int theme) {
        super(context, theme);
    }

    protected AddWeightDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View mView =View.inflate(getContext(),R.layout.activity_add_weight,null);
        this.setContentView(mView);
        ButterKnife.inject(this,mView);

        mService=new WeightRecordServiceImp(getContext());
        mStates=getContext().getResources().getStringArray(R.array.weight_state);
        initWheel();
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });

        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeightRecord weightRecord=new WeightRecord();
                double weight=wheel_weight1.getCurrentItem()+0.1*wheel_weight2.getCurrentItem();
                weightRecord.setWeight((float)weight);
                int day=wheel_day.getCurrentItem();
                Calendar newCalendar=(Calendar) calendarNow.clone();
                newCalendar.roll(Calendar.DAY_OF_YEAR, day);
                weightRecord.setDate(newCalendar.getTime());
                weightRecord.setStatus(wheel_status.getCurrentItem());

                mService.saveWeightRecord(weightRecord);


              dismiss();






            }
        });
    }
    public void initWheel()
    {

        WeightRecord weightRecord=mService.getRecentRecord();
        float rencentWeight=(float)55.5;
        if (weightRecord!=null)
            rencentWeight=mService.getRecentRecord().getWeight();
        int rencentWeightInt=(int)rencentWeight;
        float  rencentWeightFloat=rencentWeight-rencentWeightInt;

        wheel_weight1.setViewAdapter(new NumericWheelAdapter(getContext(), 0, 100));
        wheel_weight1.setCurrentItem(rencentWeightInt);
        wheel_weight1.setCyclic(true);
        wheel_weight1.setInterpolator(new AnticipateOvershootInterpolator());
        wheel_weight1.setVisibleItems(3);

        wheel_weight2.setViewAdapter(new NumericWheelAdapter(getContext(), 0, 10));
        wheel_weight2.setCurrentItem((int) (rencentWeightFloat* 10));
        wheel_weight2.setCyclic(true);
        wheel_weight2.setInterpolator(new AnticipateOvershootInterpolator());
        wheel_weight2.setVisibleItems(3);



        ArrayWheelAdapter<String> ampmAdapter =
                new ArrayWheelAdapter<String>(getContext(), mStates);
        wheel_status.setViewAdapter(ampmAdapter);
        wheel_status.setVisibleItems(3);


        calendarNow = Calendar.getInstance();

        DayArrayAdapter dayAdapter = new DayArrayAdapter(getContext(), calendarNow );
        wheel_day.setViewAdapter(dayAdapter);
        wheel_day.setCurrentItem(dayAdapter.getToday());
        wheel_day.setVisibleItems(3);



    }
}
