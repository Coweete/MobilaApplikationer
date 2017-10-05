package com.mah.ag0071.assigment2;

/**
 * Created by User on 2017-10-03.
 */

public class Member {

    private String name;
    private String id;
    private String longitude;
    private String latitude;
    private String group;



    public Member(String name){
        this.name = name;
    }

    public Member(String name,String group){
        this.name = name;
        this.group = group;
    }

    public Member(String name,String longitude,String latitude){
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
