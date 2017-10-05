package com.mah.ag0071.assigment2;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by User on 2017-10-02.
 */

public class ServerCommunications {

    public static final String TYPE = "type";
    public static final String GROUP = "group";
    public static final String GROUPS = "groups";
    public static final String MEMBER = "member";
    public static final String MEMBERS = "members";
    public static final String ID = "id";
    public static final String LONGITUDE = "longitude";
    public static final String LATITUDE = "latitude";

    public static final String TYPE_SET_POSITION = "location";
    public static final String TYPE_POSITIONS_IN_GROUPS = "locations";
    public static final String TYPE_CURRENT_GROUPS = "groups";
    public static final String TYPE_REGISTER = "register";
    public static final String TYPE_UNREGISTER = "unregister";
    public static final String TYPE_MEMBERS_IN_GROUP = "members";


    public static String currentGroups(){
        JSONObject object = new JSONObject();
        try {
            object.put(TYPE,"groups");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    public static String registration(String group,String member){
        JSONObject object = new JSONObject();
        try {
            object.put(TYPE,"register");
            object.put(GROUP,group);
            object.put(MEMBER,member);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    public static String deregistration(String id){
        JSONObject object = new JSONObject();
        try {
            object.put(TYPE,"unregister");
            object.put(ID,id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    public static String membersInGroup(String groupName){
        JSONObject object = new JSONObject();
        try{
            object.put(TYPE,"members");
            object.put(GROUP,groupName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    public static String setPosition(String id,String longitude,String latitude){
        JSONObject object = new JSONObject();
        try{
            object.put(TYPE,"location");
            object.put(ID,id);
            object.put(LONGITUDE,longitude);
            object.put(LATITUDE,latitude);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

}
