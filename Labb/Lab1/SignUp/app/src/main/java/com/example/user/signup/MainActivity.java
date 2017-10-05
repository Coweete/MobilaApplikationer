package com.example.user.signup;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int duration = Toast.LENGTH_SHORT;
    private Context context;
    private CharSequence text;
    private Button btnSignIn;
    private Button btnSignUp;
    private EditText password;
    private EditText name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
                String txt = getResources().getString(R.string.Email) + name.getText();
                showToast(toast,txt);
                txt = getResources().getString(R.string.pass) + password.getText();
                showToast(toast,txt);

                password.setText("");
                name.setText("");

            }
        });

    }

    private void showToast(Toast toast,String args){
        if(toast  == null || toast.getView().getWindowVisibility() != View.VISIBLE){
            toast = Toast.makeText(context,args,duration);
            toast.show();
        }
    }

    private void init(){
        context = getApplicationContext();
        text = getResources().getText(R.string.login_Success);

        btnSignUp = (Button) findViewById(R.id.submitBtn);
        password = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.eMail);

    }

    public void signUp(){

    }

    public void signIn(){

    }

}
