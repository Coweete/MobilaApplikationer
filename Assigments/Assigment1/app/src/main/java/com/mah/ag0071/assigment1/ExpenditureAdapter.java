package com.mah.ag0071.assigment1;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by User on 2017-09-19.
 */

public class ExpenditureAdapter extends ArrayAdapter{

    private LayoutInflater layoutInflater;
    private Expenditure[] expenditures;
    private int img[];

    public ExpenditureAdapter(Context context,Expenditure[] expenditures) {
        super(context, R.layout.listview_expenditure_adapter,expenditures);
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.expenditures = expenditures;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = (LinearLayout) layoutInflater.inflate(R.layout.listview_expenditure_adapter,parent,false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageExpList);
            holder.textTitel = (TextView) convertView.findViewById(R.id.tvListExpTitle);
            holder.textAmount = (TextView) convertView.findViewById(R.id.tvListExpAmount);
            holder.textDate = (TextView) convertView.findViewById(R.id.tvListExpDate);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        setDrawable();
        holder.imageView.setImageResource(img[position]);
        holder.textAmount.setText("" + expenditures[position].getAmount());
        holder.textTitel.setText(expenditures[position].getTitle());
        holder.textDate.setText(expenditures[position].getDate());
        return convertView;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView textTitel;
        TextView textAmount;
        TextView textDate;
    }

    public void setDrawable(){
        img = new int[expenditures.length];
        for (int i = 0; i < expenditures.length; i++) {
            switch (expenditures[i].getCategory()){
                case Expenditure.CATEGORY_FOOD:
                    img[i] = R.drawable.food;
                    break;
                case Expenditure.CATEGORY_ACCOMMONDATION:
                    img[i] = R.drawable.home_heart;
                    break;
                case Expenditure.CATEGORY_LEISURE:
                    img[i] = R.drawable.emoticon_cool;
                    break;
                case Expenditure.CATEGORY_TRAVEL:
                    img[i] = R.drawable.wallet_travel;
                    break;
                case Expenditure.CATEGORY_OTHER:
                    img[i] = R.drawable.other;
                    break;
                default:
                    break;
            }
        }
    }

    public void setExpenditures(Expenditure[] expenditures){
        this.expenditures = expenditures;
    }
}
