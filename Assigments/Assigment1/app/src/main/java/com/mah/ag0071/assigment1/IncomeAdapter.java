package com.mah.ag0071.assigment1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by User on 2017-09-18.
 */

public class IncomeAdapter extends ArrayAdapter {

    private LayoutInflater layoutInflater;
    private Incomes[] incomes;
    private int[] imageRes;

    public IncomeAdapter(Context context,Incomes[] incomes) {
        super(context, R.layout.listview_income_adapter,incomes);
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.incomes = incomes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(convertView == null){
            convertView = (LinearLayout) layoutInflater.inflate(R.layout.listview_income_adapter,parent,false);
            holder = new ViewHolder();
            holder.textViewTitle = (TextView) convertView.findViewById(R.id.tvAlltextListView);
            holder.textViewAmount = (TextView) convertView.findViewById(R.id.tvAmmountListView);
            holder.textCategory = (TextView) convertView.findViewById(R.id.tvDateListview);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageListView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        setDrawable();
        holder.imageView.setImageResource(imageRes[position]);
        holder.textViewTitle.setText(incomes[position].getTitle());
        holder.textViewAmount.setText("" + incomes[position].getAmount());
        holder.textCategory.setText(incomes[position].getDate());
        return convertView;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewAmount;
        TextView textCategory;
    }

    public void setDrawable(){
        imageRes = new int[incomes.length];
        for (int i = 0; i < incomes.length; i++) {
            switch (incomes[i].getCategory()){
                case Incomes.CATEGORY_SALARY:
                    imageRes[i] = R.drawable.salary;
                    break;
                case Incomes.CATEGORY_OTHER:
                    imageRes[i] = R.drawable.other;
                    break;
            }
        }
    }

    public void setIncomes(Incomes[] incomes){
        this.incomes = incomes;
    }
}
