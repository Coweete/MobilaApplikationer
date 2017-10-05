package com.mah.ag0071.laboratory2c;


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

    private TextView tvOutPut;

    public ViewerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_viewer, container, false);
        initializeComponent(view);
        return view;
    }

    private void initializeComponent(View view) {
        tvOutPut = (TextView) view.findViewById(R.id.tvOutPut);
    }

    public void setTvOutPut(String text){
        tvOutPut.setText(text);
    }
}
