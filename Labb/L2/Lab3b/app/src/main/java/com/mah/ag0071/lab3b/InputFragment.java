package com.mah.ag0071.lab3b;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {

    private Controller controller;
    private ListView listView;
    private View view;

    public InputFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_input, container, false);
        initializeComponents(view);
        return view;
    }

    private void initializeComponents(View view) {
        listView = (ListView) view.findViewById(R.id.lvInput);
        String[] content = {getString(R.string.what_to_do),getString(R.string.what_to_do2), getString(R.string.what_to_do3)};
        listView.setAdapter(new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_expandable_list_item_1,content));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                controller.setText(position);
            }
        });
    }


    public void setController(Controller controller){
        this.controller = controller;
    }
}
