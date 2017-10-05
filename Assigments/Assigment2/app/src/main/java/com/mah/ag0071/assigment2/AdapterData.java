package com.mah.ag0071.assigment2;

/**
 * Created by User on 2017-10-04.
 */

public class AdapterData {

    private String group;
    private String numberOfMembers;

    public AdapterData(String group){
        this.group = group;
        this.numberOfMembers = "0";
    }

    public AdapterData(String group,String numberOfMembers){
        this.group = group;
        this.numberOfMembers = numberOfMembers;
    }


    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(String numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }
}
