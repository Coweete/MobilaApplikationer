package com.mah.ag0071.assigment1;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    private LoginController controller;
    public String[] content;
    private ListView listView;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user, container, false);
        initComp(view);
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    private void initComp(View view) {
        listView = (ListView) view.findViewById(R.id.lvUsers);
        listView.setAdapter(new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_expandable_list_item_1,content));
    }

    public void setContent(String[] content){
        this.content = content;
    }


    public void setController(LoginController controller){
        this.controller = controller;
    }
}
