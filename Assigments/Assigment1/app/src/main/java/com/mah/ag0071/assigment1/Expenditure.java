package com.mah.ag0071.assigment1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 2017-09-18.
 */

public class Expenditure implements Parcelable{

    public static final String CATEGORY_FOOD = "Food";
    public static final String CATEGORY_TRAVEL = "Travel";
    public static final String CATEGORY_LEISURE = "Leisure";
    public static final String CATEGORY_ACCOMMONDATION = "Accommodation";
    public static final String CATEGORY_OTHER = "Other";


    private String category;
    private String date;
    private String username;
    private String title;
    private int amount;
    private int id;

    public Expenditure(String username,String date,String category,String title,int amount){
        this.username = username;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.title = title;
    }

    public Expenditure(int id,String username,String title,String category,String date,int amount){
        this.id = id;
        this.username = username;
        this.title = title;
        this.category = category;
        this.date = date;
        this.amount = amount;
    }

    public static final Creator<Expenditure> CREATOR = new Creator<Expenditure>(){

        @Override
        public Expenditure createFromParcel(Parcel source) {
            return new Expenditure(source.readInt(),source.readString(),
                    source.readString(),source.readString()
                    ,source.readString(), source.readInt());
        }

        @Override
        public Expenditure[] newArray(int size) {
            return new Expenditure[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(username);
        dest.writeString(title);
        dest.writeString(category);
        dest.writeString(date);
        dest.writeInt(amount);
    }



    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
