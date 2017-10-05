package com.mah.ag0071.assigment1;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private LoginController loginController;
    private Button btnSignUp;
    private Button btnLogIn;
    private TextView tvUsername;
    private TextView tvPassword;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        initComp(view);
        initRes();
        return view;
    }

    private void initRes() {
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loginController.login(tvUsername.getText().toString(),tvPassword.getText().toString());
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginController.changeToSignUp(tvUsername.getText().toString(),tvPassword.getText().toString());
            }
        });
    }

    private void initComp(View view) {
        btnLogIn = (Button) view.findViewById(R.id.btnLogin);
        btnSignUp = (Button) view.findViewById(R.id.btnloSignUp);
        tvUsername = (TextView) view.findViewById(R.id.loUserName);
        tvPassword = (TextView) view.findViewById(R.id.loPassWord);
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
