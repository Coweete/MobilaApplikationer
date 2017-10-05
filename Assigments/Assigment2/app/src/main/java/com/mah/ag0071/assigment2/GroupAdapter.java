package com.mah.ag0071.assigment2;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 2017-10-03.
 */

public class GroupAdapter extends ArrayAdapter{

    private LayoutInflater layoutInflater;
    private ArrayList<AdapterData> groups;


    public GroupAdapter(Context context, ArrayList<AdapterData> groups) {
        super(context, R.layout.listview_groups_adapter,groups);
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.groups = groups;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.listview_groups_adapter,parent,false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.listview_image);
            holder.textView1 = (TextView) convertView.findViewById(R.id.listview_tv1);
            holder.textView2 = (TextView) convertView.findViewById(R.id.listview_tv2);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageResource(R.drawable.checkbox_blank_circle_active);
        holder.textView1.setText(groups.get(position).getGroup());
        holder.textView2.setText(groups.get(position).getNumberOfMembers());
        return convertView;
    }


    private class ViewHolder{
        ImageView imageView;
        TextView textView1;
        TextView textView2;
    }
}
