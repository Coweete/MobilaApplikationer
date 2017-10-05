package com.mah.ag0071.assigment1;



import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddIncomeFragment extends Fragment {

    private MainController controller;
    private TextView tvAddAmount;
    private TextView tvAddTitle;
    private RadioGroup radioGroup;
    private Button btnAddIncome;
    private Button btnAddDate;
    private ArrayAdapter<CharSequence> adapter;
    private Spinner spinner;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private String selectedRadio;
    private String[] values;


    public AddIncomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_income, container, false);
        initComp(view);
        initRes();
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        MainActivity activity =  (MainActivity) getActivity();
        controller = activity.getController();
        controller.getAddValues();
    }

    private void initComp(final View view) {
        tvAddAmount = (TextView) view.findViewById(R.id.tvAddAmount);
        tvAddTitle = (TextView) view.findViewById(R.id.tvAddTitle);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroupAdd);
        spinner = (Spinner) view.findViewById(R.id.spListCategories);
        btnAddIncome = (Button) view.findViewById(R.id.btnAddIncome);
        btnAddDate = (Button) view.findViewById(R.id.btnPickDate);
        values = new String[6];
    }

    private void initRes() {
        adapter = ArrayAdapter.createFromResource(
                getContext(),R.array.income_category,android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinner.setAdapter(adapter);
        btnAddDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Dialog,
                        onDateSetListener,
                        year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.v("On date:", "OnDateSet: date" + year + " " + month +" " + dayOfMonth);
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
                btnAddDate.setText("" + year + "-" + mou + "-" + day);
            }
        };

        btnAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.addTransaction(radioGroup.getCheckedRadioButtonId(),btnAddDate.getText().toString(),
                        tvAddTitle.getText().toString(),spinner.getSelectedItem().toString(),tvAddAmount.getText().toString());
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.raBtnIncome:
                            adapter = ArrayAdapter.createFromResource(getContext(),R.array.income_category,
                                    android.R.layout.simple_dropdown_item_1line);
                            spinner.setAdapter(adapter);
                            selectedRadio = "income";
                        break;
                    case R.id.raBtnExpenses:
                            adapter = ArrayAdapter.createFromResource(getContext(),R.array.exp_category,
                                    android.R.layout.simple_dropdown_item_1line);
                            spinner.setAdapter(adapter);
                            selectedRadio = "expenses";
                        break;
                }
            }
        });

    }

    public void setController(MainController controller) {
        this.controller = controller;
    }

    public String[] getValue(){
        String res[] = new String[5];
        res[0] = tvAddTitle.getText().toString();
        res[1] = String.valueOf(radioGroup.getCheckedRadioButtonId());
        res[2] = String.valueOf(spinner.getSelectedItemPosition());
        res[3] = tvAddAmount.getText().toString();
        res[4] = btnAddDate.getText().toString();
        return res;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public void updateComponents() {
        tvAddTitle.setText(values[0]);
        radioGroup.check(Integer.parseInt(values[1]));
        spinner.setSelection(Integer.parseInt(values[2]));
        tvAddAmount.setText(values[3]);
        btnAddDate.setText(values[4]);
    }
}
