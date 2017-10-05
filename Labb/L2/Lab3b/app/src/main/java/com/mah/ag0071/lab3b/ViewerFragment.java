package com.mah.ag0071.lab3b;


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

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_viewer, container, false);
        initComp(view);
        return view;
    }

    private void initComp(View view) {
        textView = (TextView) view.findViewById(R.id.tvOuput);
    }

    public void updateText(String content){
        textView.setText(content);
    }

}
