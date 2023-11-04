package com.example.test3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    private Button btn_logIn;
    private TextView txt_signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_logIn = findViewById(R.id.btn_confirm);
        txt_signUp = findViewById(R.id.txt_signup);
        btn_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLogin();
            }
        });
        txt_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUpPage();
            }
        });
    }

    public void btnLogin(){

        //DO THE LOGIN VALIDATION HERE

        Intent intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    public void goToSignUpPage(){
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
}