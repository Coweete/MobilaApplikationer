package com.example.ag6505.extendsview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by tsroax on 2014-09-06.
 */
/*
public class TRLAdapter extends ArrayAdapter<String> {
    private LayoutInflater inflater;

    public TRLAdapter(Context context, String[] objects) {
        super(context, R.layout.listview_item, objects);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tvNormal;
        TRLTextView tvTrl;
        if(convertView==null) {
            convertView = (LinearLayout)inflater.inflate(R.layout.listview_item, parent, false);
        }
        tvNormal = (TextView)convertView.findViewById(R.id.tvNormal);
        tvTrl = (TRLTextView)convertView.findViewById(R.id.ttvTrl);
        tvNormal.setText(this.getItem(position));
        tvTrl.setText(this.getItem(position));
        return convertView;
    }
}
*/
public class TRLAdapter extends ArrayAdapter<String> {
    private LayoutInflater inflater;

    public TRLAdapter(Context context, String[] objects) {
        super(context, R.layout.listview_item, objects);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null) {
            convertView = (LinearLayout)inflater.inflate(R.layout.listview_item, parent, false);
            holder = new ViewHolder();
            holder.tvNormal = (TextView)convertView.findViewById(R.id.tvNormal);
            holder.tvTrl = (TRLTextView)convertView.findViewById(R.id.ttvTrl);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tvNormal.setText(this.getItem(position));
        holder.tvTrl.setText(this.getItem(position));
        return convertView;
    }

    class ViewHolder {
        TextView tvNormal;
        TRLTextView tvTrl;
    }
}
