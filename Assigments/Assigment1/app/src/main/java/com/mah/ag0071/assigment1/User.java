package com.mah.ag0071.assigment1;

import android.os.Parcel;
import android.os.Parcelable;

import javax.xml.transform.Source;

/**
 * Created by User on 2017-09-15.
 */

public class User implements Parcelable {

    private String userName;
    private String password;
    private String firstName;
    private String surName;
    private int id;

    public User(String username,String password,String firstName,String surName){
        this.userName = username;
        this.password = password;
        this.firstName = firstName;
        this.surName = surName;
    }

    public User(String username,String password,String firstName,String surName,int id){
        this.firstName = firstName;
        this.surName = surName;
        this.userName = username;
        this.id = id;
        this.password = password;
    }

    protected User(Parcel in) {
        userName = in.readString();
        password = in.readString();
        firstName = in.readString();
        surName = in.readString();
        id = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in.readString(),in.readString(),in.readString(),in.readString(),in.readInt());
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(password);
        dest.writeString(firstName);
        dest.writeString(surName);
        dest.writeInt(id);

    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }


}
