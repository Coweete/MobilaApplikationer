package com.mah.ag0071.lab3a;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {

    private Controller controller;
    private Button btnChange;
    private ListView listView;
    private String[] sColors = {"RÖD","BLÅ","GUL","GRÖN"};


    public InputFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        initializeComponents(view);
        initresources();
        return view;
    }

    private void initializeComponents(View view) {
        btnChange = (Button)  view.findViewById(R.id.btn_input);
        btnChange.setText("RED");
        listView = (ListView) view.findViewById(R.id.lvColors);
        listView.setAdapter(new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_expandable_list_item_1, sColors));

    }

    private void initresources(){

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                controller.btnText(sColors[position],position);
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.setColor();
            }
        });
    }

    public void setController(Controller controller){
        this.controller = controller;
    }


    public void setBtnText(String btnText) {
        btnChange.setText(btnText);
    }
}
