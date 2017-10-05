package com.mah.ag0071.assigment1;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowUserFragment extends Fragment {

    private TextView tvFirstName;
    private TextView tvSurName;
    private MainController controller;


    public ShowUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_user, container, false);
        initComp(view);
        return view;
    }


    private void initComp(View view) {
        tvFirstName = (TextView) view.findViewById(R.id.tvFristnameMain);
        tvSurName = (TextView) view.findViewById(R.id.tvSurnameMain);
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }

    public void setText(String firstname,String surname){
        tvFirstName.setText(firstname);
        tvSurName.setText(surname);
    }
}
