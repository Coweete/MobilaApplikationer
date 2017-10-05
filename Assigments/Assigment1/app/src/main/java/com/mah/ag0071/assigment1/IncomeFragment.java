package com.mah.ag0071.assigment1;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
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
public class IncomeFragment extends Fragment {

    private ListView listView;
    private MainController controller;
    private Incomes[] incomes;
    private Button btnStartDate;
    private Button btnEndDate;
    private Button btnShowDates;
    private Button btnBack;
    private DatePickerDialog startDialog,  endDialog;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    public IncomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
        MainActivity activity = (MainActivity) getActivity();
        controller = activity.getController();
        Incomes[] incomess = controller.incomeValues();
        listView.setAdapter(new IncomeAdapter(getContext(),incomess));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_income, container, false);
        initComp(view);
        initRes();
        return view;
    }

    private void initComp(View view) {
        listView = (ListView) view.findViewById(R.id.lvIncome);
        btnShowDates = (Button) view.findViewById(R.id.btnIncSortAfterDate);
        //btnBack = (Button) view.findViewById(R.id.btnIncBack);
        btnStartDate = (Button) view.findViewById(R.id.btnIncStartDate);
        btnEndDate = (Button) view.findViewById(R.id.btnIncEndDate);
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
                }else if (endDialog.getDatePicker() == view){
                    btnEndDate.setText("" + year + "-" + mou + "-" + day);
                }

            }
        };


        /*
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.swapToShowOverview();
            }
        });
        */
        btnShowDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.showIncomeDate(btnStartDate.getText().toString(),btnEndDate.getText().toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Incomes res = (Incomes) listView.getItemAtPosition(position);
                String hello = "Title: " + res.getTitle()+ "\nAmount:" + res.getAmount() + "kr"
                        + "\nDate: " + res.getDate() + "\n Position:" + (position +1);
                Toast toast = Toast.makeText(getContext(),hello,Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }


    public void setController(MainController controller) {
        this.controller = controller;
    }

    public void setIncomes(Incomes[] incomes){
        this.incomes = incomes;
    }

    public void changeAdapter(Incomes[] incomes){
        listView.setAdapter(new IncomeAdapter(getContext(),incomes));
    }

    public String[] getValues() {
        String res[] = new String[2];
        res[0] = btnStartDate.getText().toString();
        res[1] = btnEndDate.getText().toString();
        return res;
    }


    public void updateComponents(String[] values) {
        btnStartDate.setText(values[0]);
        btnEndDate.setText(values[1]);
    }
}
