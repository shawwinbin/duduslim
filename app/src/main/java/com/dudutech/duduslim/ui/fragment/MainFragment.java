package com.dudutech.duduslim.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dudutech.duduslim.R;
import com.dudutech.duduslim.view.ArcsProgress;
import com.dudutech.duduslim.view.BarView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by winbin on 2014/4/18.
 */
public class MainFragment extends BaseFragment {
    @InjectView(R.id.ap_reduce)
    ArcsProgress ap_reduce;
    @InjectView(R.id.bar_view)
    BarView bar_view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main,null);
        ButterKnife.inject(this, view);
        ap_reduce.setValue(81);
        randomSet(bar_view);

        return view;
    }

    private void randomSet(BarView barView){
        int random = 6;
        ArrayList<String> test = new ArrayList<String>();
        for (int i=0; i<random; i++){
            test.add("4.2"+i);

//            test.add(String.valueOf(i+1));
        }
        barView.setBottomTextList(test);


        ArrayList<Integer> barDataList = new ArrayList<Integer>();
        for(int i=0; i<random; i++){
            barDataList.add((int)(Math.random() * 100));
        }
        barView.setDataList(barDataList,100);
    }
}
