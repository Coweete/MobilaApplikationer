package com.mah.ag0071.assigment2;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsFragment extends Fragment {

    private ListView listView;
    private ArrayList<AdapterData> groups;
    private GroupAdapter adapter;
    private HashMap<String, ArrayList<Member>> data;
    private Controller controller;


    public GroupsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_groups, container, false);
        initComponents(view);
        initRes();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity mainActivity = (MainActivity) getActivity();
        controller = mainActivity.getController();
        try{
            listView.setAdapter(adapter);
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    private void initComponents(View view) {
        listView = (ListView) view.findViewById(R.id.lv_groups);
        groups = new ArrayList<>();
        groups.add(new AdapterData("h"));
        adapter = new GroupAdapter(view.getContext(),groups);
        groups.remove(0);
    }

    private void initRes() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AdapterData temp = (AdapterData) listView.getItemAtPosition(i);
                controller.collectMembersInSelectedGroup(temp);
            }
        });

    }

    public void setGroups(HashMap<String,AdapterData> groups){
        Log.v("Connection","In set group");
        Log.v("Connection","Group size: " + groups.size());
        ArrayList<AdapterData> ttt = new ArrayList<>(groups.values());
        adapter.clear();
        for (int i = 0; i < groups.size(); i++) {
            adapter.add(ttt.get(i));
        }
        adapter.notifyDataSetChanged();

    }


    public void setData(HashMap<String, ArrayList<Member>> data) {
        this.data = data;
    }
}
