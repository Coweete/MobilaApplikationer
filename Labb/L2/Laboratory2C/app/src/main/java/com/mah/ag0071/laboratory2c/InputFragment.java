package com.mah.ag0071.laboratory2c;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {

    private Button btnClick;
    private Button btnReset;
    private Controller controller;

    public InputFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        initializeComponent(view);
        initializeRes();
        return view;
    }

    private void initializeRes() {

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.pushedButton();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.resetCounter();
            }
        });
    }

    private void initializeComponent(View view) {
        btnClick = (Button) view.findViewById(R.id.btnClick);
        btnReset = (Button) view.findViewById(R.id.btnreset);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
