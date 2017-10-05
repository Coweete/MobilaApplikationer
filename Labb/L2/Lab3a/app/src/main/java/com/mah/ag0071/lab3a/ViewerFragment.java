package com.mah.ag0071.lab3a;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewerFragment extends Fragment {

    private TextView textView;

    public ViewerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_viewer, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        textView = (TextView) view.findViewById(R.id.tvView);
    }

    public void setColor(int color){
        textView.setBackgroundColor(color);
    }

}
