package com.dudutech.duduslim.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dudutech.duduslim.R;
import com.dudutech.duduslim.entity.WeightRecord;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 14-1-20.
 */
public class WeightRecordAdapter extends BaseAdapter {

    private List<WeightRecord> mlist;

    private SimpleDateFormat sdf;
    protected Context mContext;
    private String[] mStates;


    public WeightRecordAdapter( Context context,List<WeightRecord> mlist) {
        mContext = context;
        this.mlist = mlist;
        mStates=mContext.getResources().getStringArray(R.array.weight_state);
        sdf=new SimpleDateFormat("MM-dd");
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       ViewHolder holder ;
        if ( convertView ==null) {

            convertView = View.inflate(mContext, R.layout.item_weight_record, null);
            holder= new ViewHolder();
            holder.tv_weight= (TextView) convertView.findViewById(R.id.tv_weight);
            holder.tv_date= (TextView) convertView.findViewById(R.id.tv_date);
            holder.tv_status=(TextView)convertView.findViewById(R.id.tv_status);

            convertView.setTag(holder);
        } else {
           holder= (ViewHolder) convertView.getTag();
        }

        holder.tv_weight.setText(mlist.get(position).getWeight()+"kg");
        holder.tv_date.setText(sdf.format(mlist.get(position).getDate()));

        holder.tv_status.setText(mStates[mlist.get(position).getStatus()]);





        return convertView;
    }



    public static class ViewHolder {
        TextView tv_weight;
        TextView tv_date;
        TextView tv_status;
    }
}
