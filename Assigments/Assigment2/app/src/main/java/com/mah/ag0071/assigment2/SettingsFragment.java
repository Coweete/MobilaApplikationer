package com.mah.ag0071.assigment2;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private Spinner spinner;
    private Switch swicthGroup;
    private Button btnChange;
    private EditText tvName;
    private EditText tvGroup;
    private ArrayAdapter<String> arrayAdapter;
    private boolean switchState;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_settings, container, false);
        initComp(view);
        initRes();
        return view;
    }

    @Override
    public void onResume() {
        swicthGroup.setChecked(false);
        spinner.setEnabled(false);
        tvGroup.setEnabled(true);
        super.onResume();
    }

    private void initRes() {
        spinner.setAdapter(arrayAdapter);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeGroup();
            }
        });

        swicthGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (swicthGroup.isChecked()){
                    ((MainActivity) getActivity()).getController().updateAdapter();
                    spinner.setEnabled(true);
                    tvGroup.setEnabled(false);
                }else{
                    spinner.setEnabled(false);
                    tvGroup.setEnabled(true);
                }
            }
        });
    }

    private void changeGroup() {
        try{
            Controller controller = ((MainActivity) getActivity()).getController();
            if (swicthGroup.isChecked()){
                controller.changeGroup(tvName.getText().toString(),spinner.getSelectedItem().toString());
            }else {
                controller.changeGroup(tvName.getText().toString(),tvGroup.getText().toString());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void initComp(View view) {
        spinner = (Spinner) view.findViewById(R.id.settingSpinner);
        swicthGroup = (Switch) view.findViewById(R.id.settingSwitch);
        btnChange = (Button) view.findViewById(R.id.btnAdd);
        tvName = (EditText) view.findViewById(R.id.tvName);
        tvGroup = (EditText) view.findViewById(R.id.tvGroup);
        arrayAdapter = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_expandable_list_item_1);

    }

    public void updateSpinner(HashMap<String,AdapterData> groups){
        ArrayList<AdapterData> temp = new ArrayList<>(groups.values());
        Log.v("Settings","In settings ");
        arrayAdapter.clear();
        for (int i = 0; i < temp.size(); i++) {
            arrayAdapter.add(temp.get(i).getGroup());
        }
        Log.v("Settings","Array adater has data? " + arrayAdapter.getCount());
        arrayAdapter.notifyDataSetChanged();
    }

}
