package com.example.test3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");
    private Button btn_logIn;
    private TextView txt_signUp;
    private EditText txt_email;
    private EditText txt_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_logIn = findViewById(R.id.btn_confirm);
        txt_signUp = findViewById(R.id.txt_signup);
        txt_email = findViewById(R.id.email);
        txt_password = findViewById(R.id.password);
        btn_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLogin();
            }
        });
        txt_email.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                validateEmail();
                return false;}
        });
        txt_password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                validatePassword();
                return false;}
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
    private boolean validateEmail(){
       String input_email = txt_email.getText().toString().trim();

       if(input_email.isEmpty()){
           txt_email.setError("Field cannot be empty");
           return false;
       } else if(!Patterns.EMAIL_ADDRESS.matcher(input_email).matches()){
           txt_email.setError("Invalid email address");
       } else{
           txt_email.setError(null);
           return true;
       }
       return false;
    }
    private boolean validatePassword(){
        String input_password = txt_password.getText().toString().trim();

        if(input_password.isEmpty()){
            txt_password.setError("Field cannot be empty");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(input_password).matches()){
            txt_password.setError("Password too weak");
        } else{
            txt_password.setError(null);
            return true;
        }
        return false;
    }
}