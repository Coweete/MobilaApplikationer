package com.mah.ag0071.assigment2;

import android.Manifest;
import android.app.DialogFragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Controller {


    public static final int REQUEST_ACCESS_FINE_LOCATION = 1337;
    private MainActivity activity;
    private TCPConnection connection;
    private boolean bound = false;
    private boolean connected = false;
    private ServiceConnection serviceConn;
    private Listener listener;
    private LocationManager locationManager;
    private MapFragment mapFragment;
    private GroupsFragment groupsFragment;
    private Member currentUser;
    private WelcomeDialog welcomeDialog;
    private LocList locationListener;
    private int counter = 0;
    private UpdateTImer updateTImer;
    private HashMap<String,AdapterData> groups;
    private String activeGroup;
    private HashMap<String,ArrayList<Member>> groupAndMember;
    private SettingsFragment settingsFragment;
    private int activeFragment;


    public Controller(MainActivity mainActivity,Bundle savedInstanceState){
        this.activity = mainActivity;
        connection = new TCPConnection();
        //this.testing(savedInstanceState);
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocList(this);
        mapFragment = new MapFragment();
        mapFragment.setResorces(activity,this);
        groupsFragment = new GroupsFragment();
        currentUser = new Member("Hejsan1337","Solo");
        activeGroup = currentUser.getGroup();
        startConnection(savedInstanceState);
        welcomeDialog = new WelcomeDialog();
        activity.startDialog(welcomeDialog);
        updateTImer = new UpdateTImer(this);
        groups = new HashMap<>();
        groupAndMember = new HashMap<>();
        settingsFragment = new SettingsFragment();
    }

    private void startConnection(Bundle savedInstanceState){
        Intent intent = new Intent(activity,TCPConnection.class);
        intent.putExtra(TCPConnection.IP,"195.178.227.53");
        intent.putExtra(TCPConnection.PORT,"7117");
        if (savedInstanceState == null){
            Log.v("Connection","Start service?");
            activity.startService(intent);
            Log.v("Connection","Start service");
        }
        serviceConn = new ServiceConn();
        boolean result = activity.bindService(intent,serviceConn,0);
        if(!result){
            Log.v("Connection","No binding!");
        }else {

        }
    }

    public void onDestroy() {
        Log.v("Connection","On destroy");
        String msg = ServerCommunications.deregistration(currentUser.getId());
        connection.send(msg);
        connection.disconnect();
        activity.unbindService(serviceConn);
        Intent intent = new Intent(activity,TCPConnection.class);
        activity.stopService(intent);
        updateTImer.stopTimer();
    }


    public void onResume() {
        if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_ACCESS_FINE_LOCATION);
        }else {
            try {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            }catch (Exception e){
                Log.e("OnResume in controller",e.toString());
            }

        }

    }

    public void onRequestPermissionResult(int requestCode) {
        switch (requestCode){
            case Controller.REQUEST_ACCESS_FINE_LOCATION:
                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                }
        }
    }

    public void setStartFragment() {
        activeFragment = 1;
        activity.setFragment(mapFragment,false);
    }

    public void onOptionsItemSelected(int id) {
        Log.v("Controller","Swaping fragment? id=" + id);
        String msg;
        switch (id){
            case R.id.drawer_map:
                activeFragment = 1;
                Log.v("Map","Current user Long:" + currentUser.getLongitude() + ",Lat: " + currentUser.getLatitude());
                mapFragment.updateMapPosition(currentUser);
                activity.setFragment(mapFragment,false);
                break;
            case R.id.drawer_groups:
                activeFragment = 2;
                updateAdapter();
                Log.v("Connection","Before setting fragment");
                activity.setFragment(groupsFragment,false);
                break;
            case R.id.drawer_settings:
                activeFragment = 3;
                updateAdapter();
                activity.setFragment(settingsFragment,false);
                break;
        }
    }

    public void updateAdapter(){
        String msg;
        msg = ServerCommunications.currentGroups();
        groups.clear();
        connection.send(msg);
    }

    public void registerUser() {
        String msgToSend = ServerCommunications.registration(currentUser.getGroup(),currentUser.getName());
        connection.send(msgToSend);
    }


    public void timerUpdate() {
        Log.v("Timer", "Timercounter");
        currentUser.setLongitude(String.valueOf(locationListener.getLongitude()));
        currentUser.setLatitude(String.valueOf(locationListener.getLatitude()));
        String msg  = ServerCommunications.setPosition(currentUser.getId(),currentUser.getLongitude(),currentUser.getLatitude());
        Log.v("Connection","Json object to send: " + msg);
        connection.send(msg);
    }

    public void collectMembersInSelectedGroup(AdapterData temp) {
        String msg = ServerCommunications.membersInGroup(temp.getGroup());
        connection.send(msg);
    }

    public void setMapMarkers(GoogleMap googleMap) {
        try{
            Log.v("Map","Before adding thins to the map in controller");
            googleMap.clear();
            ArrayList<Member> memo = new ArrayList<>(groupAndMember.get(activeGroup));
            for (int i = 0; i < memo.size(); i++) {
                LatLng temp = new LatLng(Double.parseDouble(memo.get(i).getLatitude()),
                        Double.parseDouble(memo.get(i).getLongitude()));
                googleMap.addMarker(new MarkerOptions().position(temp).title(memo.get(i).getName()));
            }
            LatLng tt = new LatLng(Double.parseDouble(memo.get(0).getLatitude()),
                    Double.parseDouble(memo.get(0).getLongitude()));
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(tt, 15);
            googleMap.animateCamera(cameraUpdate);
            Log.v("Map","End of adding and fixing pos to the map in controller");
        }catch (NullPointerException e){
            Log.e("Map","Nullpointer i currentUser");
            e.printStackTrace();
        }
    }

    public void pressedFloating() {
        activity.setFragment(mapFragment,false);
    }

    public void changeGroup(String username, String group) {
        Log.v("Connection","Change group");
        String msg;
        try{
            Log.v("Connection","Current user id: " + currentUser.getId());
            msg = ServerCommunications.deregistration(currentUser.getId());
            connection.send(msg);
            currentUser = new Member(username,group);
            msg = ServerCommunications.registration(currentUser.getGroup(),currentUser.getName());
            connection.send(msg);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private class ServiceConn implements ServiceConnection{
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            TCPConnection.LocalService ls = (TCPConnection.LocalService) iBinder;
            connection = ls.getService();
            bound = true;
            listener = new Listener();
            listener.start();
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            bound = false;
        }
    }

    private class Listener extends Thread{
        public void stopListen(){
            interrupt();
            listener = null;

        }
        public void run(){
            String message;

            while (listener != null){
                try{
                    message = connection.receive();
                    Log.v("Connection","Message from server: " + message);
                    JSONObject object = new JSONObject(message);
                    String type = object.getString(ServerCommunications.TYPE);
                    JSONArray array;
                    switch (type){
                        case ServerCommunications.TYPE_CURRENT_GROUPS:
                            Log.v("Connection","In current groups");
                            array = object.getJSONArray(ServerCommunications.GROUPS);
                            JSONObject temp;
                            for (int i = 0; i < array.length(); i++) {
                                temp = array.getJSONObject(i);
                                groups.put(temp.getString(ServerCommunications.GROUP),new AdapterData(temp.getString(ServerCommunications.GROUP)));
                            }
                            if (activeFragment == 2){
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        groupsFragment.setGroups(groups);
                                    }
                                });
                            }else if (activeFragment == 3){
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        settingsFragment.updateSpinner(groups);
                                    }
                                });
                            }
                            break;
                        case ServerCommunications.TYPE_MEMBERS_IN_GROUP:
                            Log.v("Connection","Members in group");
                            array = object.getJSONArray(ServerCommunications.MEMBERS);
                            AdapterData newData = new AdapterData(object.getString(ServerCommunications.GROUP),"" + array.length());
                            groups.put(newData.getGroup(),newData);
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    groupsFragment.setGroups(groups);
                                }
                            });
                            break;
                        case ServerCommunications.TYPE_POSITIONS_IN_GROUPS:
                            Log.v("Connection","Auto message from server");
                            array = object.getJSONArray(ServerCommunications.TYPE_SET_POSITION);
                            ArrayList<Member> tempMemberArray = new ArrayList<>();
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject aa = array.getJSONObject(i);
                                tempMemberArray.add(i,new Member(aa.getString(ServerCommunications.MEMBER),
                                        aa.getString(ServerCommunications.LONGITUDE),
                                        aa.getString(ServerCommunications.LATITUDE)));

                            }
                            groupAndMember.put(activeGroup,tempMemberArray);
                            Log.v("Connection","After Auro");
                            break;
                        case ServerCommunications.TYPE_REGISTER:
                            Log.v("Connection","Type register answer");
                            currentUser.setId(object.getString(ServerCommunications.ID));
                            currentUser.setGroup(object.getString(ServerCommunications.GROUP));
                            Log.v("User",currentUser.toString());
                            //Start timer here
                            updateTImer.startTimer();
                            break;
                        case ServerCommunications.TYPE_UNREGISTER:
                            Log.v("Connection","Successfully unregister the user");
                            break;
                        case ServerCommunications.TYPE_SET_POSITION:
                            Log.v("Connection","Successfully set a position");
                            break;
                        default:
                            break;
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
