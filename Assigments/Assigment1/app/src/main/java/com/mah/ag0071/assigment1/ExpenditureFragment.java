package com.mah.ag0071.assigment1;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpenditureFragment extends Fragment {

    private ListView listView;
    private MainController mainController;
    private Expenditure[] expenditure;
    private Button btnStartDate;
    private Button btnEndDate;
    private Button btnShowDate;
    private Button btnBack;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private DatePickerDialog startDialog,endDialog;

    public ExpenditureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity mainActivity = (MainActivity) getActivity();
        mainController = mainActivity.getController();
        Expenditure[] expenditures = mainController.expenValues();
        listView.setAdapter(new ExpenditureAdapter(getContext(),expenditures));

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expenditure, container, false);
        initComp(view);
        initRes();
        return view;
    }

    private void initComp(View view) {
        listView = (ListView) view.findViewById(R.id.lvExpenditure);
        btnStartDate = (Button) view.findViewById(R.id.btnExpStartDate);
        btnEndDate = (Button) view.findViewById(R.id.btnExpEndDate);
        btnShowDate = (Button) view.findViewById(R.id.btnExpShowDate);
        //btnBack = (Button) view.findViewById(R.id.btnExpBack);
    }

    private void initRes() {

        btnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                startDialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Dialog,
                        onDateSetListener,
                        year,month,day
                );

                startDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                startDialog.show();
            }
        });

        btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                endDialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Dialog,
                        onDateSetListener,
                        year,month,day
                );
                endDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                endDialog.show();
            }
        });

        btnShowDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainController.showExpDate(btnStartDate.getText().toString(),
                        btnEndDate.getText().toString());
            }
        });
    /*
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainController.swapToShowOverview();
            }
        });
    */
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String mou,day;
                if (month < 10){
                    mou = "0" + month;
                }else {
                    mou = "" + month;
                }
                if (dayOfMonth < 10){
                    day = "0" + dayOfMonth;
                }else {
                    day = "" + dayOfMonth;
                }
                if (startDialog.getDatePicker() == view){
                    btnStartDate.setText("" + year + "-" + mou + "-" + day);
                }else if(endDialog.getDatePicker() == view){
                    btnEndDate.setText("" + year + "-" + mou + "-" + day);
                }
            }
        };

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Expenditure res = (Expenditure) listView.getItemAtPosition(position);
                String hello = "Title: " + res.getTitle()+ "\nAmount:" + res.getAmount() + "kr"
                        + "\nDate: " + res.getDate() + "\n Position:" + (position + 1);
                Toast toast = Toast.makeText(getContext(),hello,Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setExpenditure(Expenditure[] expenditure) {
        Log.v("Frag","Expen in setExpen: " + expenditure.length);
        this.expenditure = expenditure;
    }

    public void changeAdapter(Expenditure[] sortedExpend) {
        listView.setAdapter(new ExpenditureAdapter(getContext(),sortedExpend));
    }

    public String[] getValues() {
        String res[] = new String[2];
        res[0] = btnStartDate.getText().toString();
        res[1] = btnEndDate.getText().toString();
        return res;
    }

    public void updateComponents(String[] values){
        btnStartDate.setText(values[0]);
        btnEndDate.setText(values[1]);
    }
}
