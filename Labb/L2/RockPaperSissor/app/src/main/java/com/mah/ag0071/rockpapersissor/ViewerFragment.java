package com.mah.ag0071.rockpapersissor;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewerFragment extends Fragment {



    public ViewerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_viewer, container, false);
    }

    public void setHumanPoints(int humanPoints) {

    }

    public void setComputerPoints(int computerPoints) {

    }

    public void setHumanChoice(int humanChoice) {

    }

    public void setComputerChoice(int computerChoice) {

    }

    public void humanWinner() {

    }

    public void computerWinner() {

    }
}
