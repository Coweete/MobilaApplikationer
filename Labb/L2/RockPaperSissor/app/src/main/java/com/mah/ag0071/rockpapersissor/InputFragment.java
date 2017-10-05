package com.mah.ag0071.rockpapersissor;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {

    private RPSController controller;

    public InputFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false);
    }

    public void setController(RPSController controller) {
        this.controller = controller;
    }

    public void enableChoiceButtons(boolean buttonState) {

    }
}
