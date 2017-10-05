package com.mah.ag0071.assigment1;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends Fragment {

    private MainController mainController;
    private TextView tvFirstname;
    private TextView tvSurname;
    private TextView tvSaldo;
    private TextView tvIncSaldo;
    private TextView tvExpSaldo;
    private Button btnShowIncome;
    private Button btnShowExpenses;
    private Button btnShowAddView;
    private Button btnManUpdate;


    public OverviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_overview, container, false);
        initComp(view);
        initRes();
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
        MainActivity activity = (MainActivity) getActivity();
        mainController = activity.getController();
        String[] array = mainController.updateOverView();
        setText(array[0],array[1],array[2],array[3],array[4]);

    }

    private void initRes() {
        btnShowIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainController.swapToShowIncome();
            }
        });

        btnShowExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainController.swapToShowExpenses();
            }
        });

        btnShowAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainController.swapToShowAdd();
            }
        });

        btnManUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainController.updateOverView();
            }
        });
    }

    private void initComp(View view) {
        tvFirstname = (TextView) view.findViewById(R.id.tvFirstnameOverview);
        tvSurname = (TextView) view.findViewById(R.id.tvSurnameOverview);
        tvSaldo = (TextView) view.findViewById(R.id.tvOverSaldo);
        tvIncSaldo = (TextView) view.findViewById(R.id.tvOverIncome);
        tvExpSaldo = (TextView) view.findViewById(R.id.tvOverExpen);
        btnShowExpenses = (Button) view.findViewById(R.id.btnShowExpend);
        btnShowIncome = (Button) view.findViewById(R.id.btnShowIncome);
        btnShowAddView = (Button) view.findViewById(R.id.btnAddTransaction);
        btnManUpdate = (Button) view.findViewById(R.id.btnManUpdate);
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setText(String firstName, String surName,String saldo,String incSaldo,String expSaldo) {
        Log.v("HELP","In overview " + firstName + " " + surName + " " + saldo + " " + incSaldo + " " + expSaldo);
        try{
            tvFirstname.setText(firstName);
            tvSurname.setText(surName);
            tvSaldo.setText(saldo);
            tvIncSaldo.setText(incSaldo);
            tvExpSaldo.setText(expSaldo);
        }catch (Exception e){
            Log.e("Error",e.toString());
        }

    }
}
