package com.mah.ag0071.assigment1;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    private LoginController controller;
    private Button btnSuSignUp;
    private Button btnSuLogIn;
    private Button btnShowUsers;
    private Button btnClearUsers;
    private TextView tvSuUsername;
    private TextView tvSuPassword;
    private TextView tvFirstname;
    private TextView tvSurname;
    private String[] values;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        initComp(view);
        initRes();
        return view;
    }

    private void initRes() {
        btnSuSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.addUser(tvSuUsername.getText().toString(),tvSuPassword.getText().toString(),tvFirstname.getText().toString(),tvSurname.getText().toString());
            }
        });

        btnSuLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.changeToLogin(tvSuUsername.getText().toString(),tvSuPassword.getText().toString());
            }
        });

        /*
        btnShowUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.showUsers();
            }
        });
        btnClearUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.deleteAllUsers();
            }
        });
        */
    }

    private void initComp(View view) {
        btnSuLogIn = (Button) view.findViewById(R.id.btnsuLogin);
        btnSuSignUp = (Button) view.findViewById(R.id.btnsuSignUp);
        //btnShowUsers = (Button) view.findViewById(R.id.btnShowUsers);
        //btnClearUsers = (Button) view.findViewById(R.id.btnDeleteAll);
        tvSuPassword = (TextView) view.findViewById(R.id.tvSuPassWord);
        tvSuUsername = (TextView) view.findViewById(R.id.tvSuUserName);
        tvFirstname = (TextView) view.findViewById(R.id.tvFirstname);
        tvSurname = (TextView) view.findViewById(R.id.tvSurName);
    }

    public void setLoginController(LoginController controller){
        this.controller = controller;
    }

    public String[] getValues() {
        String[] tempValues = new String[3];
        tempValues[0] = tvSuUsername.getText().toString();
        tempValues[1] = tvFirstname.getText().toString();
        tempValues[2] = tvSurname.getText().toString();
        return tempValues;
    }

}
