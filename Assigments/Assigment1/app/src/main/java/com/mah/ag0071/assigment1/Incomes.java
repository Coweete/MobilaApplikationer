package com.mah.ag0071.assigment1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by User on 2017-09-14.
 */

public class Incomes implements Parcelable{

    public static final String CATEGORY_SALARY = "Salary";
    public static final String CATEGORY_OTHER = "Other";

    private String username;
    private String date;
    private int amount;
    private int id;
    private String title;
    private String category;


    public Incomes(String date,int amount,String title,String category,String username){
        this.date = date;
        this.amount = amount;
        this.title = title;
        this.username = username;
        this.category = category;
    }

    public Incomes(int id,String username,String category,String date,String title,int amount){
        this.id = id;
        this.username = username;
        this.category = category;
        this.date = date;
        this.title = title;
        this.amount = amount;
    }

    public static final Creator<Incomes> CREATOR = new Creator<Incomes>() {
        @Override
        public Incomes createFromParcel(Parcel source) {
            return new Incomes(source.readInt(),source.readString(),
                            source.readString(),source.readString(),
                            source.readString(),source.readInt());
        }

        @Override
        public Incomes[] newArray(int size) {
            return new Incomes[size];
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
        dest.writeString(category);
        dest.writeString(date);
        dest.writeString(title);
        dest.writeInt(amount);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
