package com.dudutech.duduslim.service;

import com.dudutech.duduslim.entity.WeightRecord;

import java.util.List;

/**
 * Created by winbin on 2014/4/25.
 */
public interface WeightRecordService {
    public boolean saveWeightRecord(WeightRecord weightRecord);

    public boolean deleteWeightRecord(int id);

    public List<WeightRecord> getAllWeightRecord();




}
