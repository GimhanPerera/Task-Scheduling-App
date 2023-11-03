package com.example.test3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class SignUpActivity extends AppCompatActivity {
    private Button btn_signUp;
    private TextView txt_backToSignIn;
    private TextInputLayout layout_firstName;
    private TextInputLayout layout_lastName;
    private TextInputLayout layout_email;
    private TextInputLayout layout_password;
    private TextInputLayout layout_confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_signUp = findViewById(R.id.btn_signUp);//Sign up button
        txt_backToSignIn = findViewById(R.id.sighIn_link);
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

    public void sign_up() {

        //DO SIGN UP VALIDATION HERE

        //password validations
        TextInputLayout layoutPassword = findViewById(R.id.layout_password);
        TextInputEditText TextPassword = findViewById(R.id.password);

        TextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String Password = s.toString();

                if (Password.length() >= 8) {
                    Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
                    Matcher matcher = pattern.matcher(Password);
                    boolean isPwdContainSpeChar = matcher.find();
                    if (isPwdContainSpeChar) {
                        layoutPassword.setHelperText("Strong password");
                        layoutPassword.setError("");

                    } else {
                        layoutPassword.setHelperText("");
                        layoutPassword.setError("Password should contain at 1 special character");
                    }


                } else {
                    layoutPassword.setHelperText("");
                    layoutPassword.setError("Enter minimum 8 characters");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void goToSignInPage() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}