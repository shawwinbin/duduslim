package com.dudutech.duduslim.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dudutech.duduslim.R;
import com.dudutech.duduslim.entity.WeightRecord;
import com.dudutech.duduslim.service.WeightRecordService;
import com.dudutech.duduslim.service.WeightRecordServiceImp;
import com.dudutech.duduslim.ui.adapter.WeightRecordAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by winbin on 2014/4/18.
 */
public class RecordFragment extends BaseFragment {
    @InjectView(R.id.lv_record)
    ListView lv_record;
    private List<WeightRecord> mlist;

    private WeightRecordService mService;

    private WeightRecordAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        mlist=new ArrayList<WeightRecord>();
        mService=new WeightRecordServiceImp(getActivity());
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_record,null);
        ButterKnife.inject(this, view);
        initData();
        return view;
    }


    private void initData()
    {
        mlist=mService.getAllWeightRecord();
        mAdapter=new WeightRecordAdapter(getActivity(),mlist);

        lv_record.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();



    }
}
