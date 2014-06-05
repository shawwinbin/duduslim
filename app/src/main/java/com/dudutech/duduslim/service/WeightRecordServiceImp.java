package com.dudutech.duduslim.service;

import android.content.Context;

import com.dudutech.duduslim.db.DatabaseHelper;
import com.dudutech.duduslim.entity.WeightRecord;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by winbin on 2014/4/25.
 */
public class WeightRecordServiceImp implements   WeightRecordService {

    private Context mContext;
    private DatabaseHelper dbHelper;

    public  WeightRecordServiceImp(Context mContext)
    {
        this.mContext=mContext;
        dbHelper= OpenHelperManager.getHelper(mContext,DatabaseHelper.class);
    }

    @Override
    public boolean saveWeightRecord(WeightRecord weightRecord) {
        try {
            dbHelper.getWeightRecordDao().createIfNotExists(weightRecord);

        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
        return true;
    }

    @Override
    public boolean deleteWeightRecord(int id) {

        try {
            dbHelper.getWeightRecordDao().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }

        return true;
    }

    @Override
    public List<WeightRecord> getAllWeightRecord() {
        try {
            return  dbHelper.getWeightRecordDao().queryBuilder().orderBy("date",false).query();
//            return dbHelper.getWeightRecordDao().queryForAll();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public WeightRecord getRecentRecord() {

        try {
            return  dbHelper.getWeightRecordDao().queryBuilder().orderBy("date",false).queryForFirst();
//            return dbHelper.getWeightRecordDao().queryForAll();

        } catch (SQLException e) {
            e.printStackTrace();
            return new WeightRecord();
        }


    }
}
