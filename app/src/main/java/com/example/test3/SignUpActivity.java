package com.example.test3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {
    private Button btn_signUp;
    private TextView txt_backToSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_signUp = findViewById(R.id.btn_SignUp);//Sign up button
        txt_backToSignIn = findViewById(R.id.SignInLink);
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_up();
            }
        });
        txt_backToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignInPage();
            }
        });
    }

    public void sign_up(){

        EditText txt_FirstName, txt_LastName, txt_Email, txt_Password, txt_ConfirmPassword;
        Button btn_SignUp;

        //DO SIGN UP VALIDATION HERE


        Intent intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    public void goToSignInPage(){

        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}