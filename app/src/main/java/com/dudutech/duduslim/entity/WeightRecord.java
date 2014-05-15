package com.dudutech.duduslim.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by winbin on 2014/4/25.
 */

@DatabaseTable(tableName = "tb_weightrecord")
public class WeightRecord {

    public final static int STATE_EMPTY=0,STATE_BEFORE_LUNCH=1,STATE_AFTER_LUNCH=2,STATE_BEFORE_SLEEP=3;
    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    float weight;
    @DatabaseField
    Date date;
    @DatabaseField
    int status;

    public WeightRecord() {

    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getStatus() {
        return status;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
