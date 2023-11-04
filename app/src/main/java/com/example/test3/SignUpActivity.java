package com.example.test3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
//import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

//import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +
                    "(?=.*[a-z])" +
                    "(?=.*[A-Z])" +
                    "(?=.*[a-zA-Z])" +
                    "(?=.*[@#$%^&+=])" +
                    "(?=\\S+$)" +
                    ".{8,}" +
                    "$");
    private Button btn_signUp;
    private TextView txt_backToSignIn;
    private TextInputEditText input_firstName;
    private TextInputLayout layout_firstName;
    private TextInputEditText input_lastName;
    private TextInputLayout layout_lastName;
    private TextInputEditText input_email;
    private TextInputLayout layout_email;
    private TextInputEditText input_password;
    private TextInputLayout layout_password;
    private TextInputEditText input_confirmPassword;
    private TextInputLayout layout_confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_signUp = findViewById(R.id.btn_signUp);//Sign up button
        txt_backToSignIn = findViewById(R.id.sighIn_link);
        input_firstName = findViewById(R.id.firstName);
        layout_firstName = findViewById(R.id.layout_firstName);
        input_lastName = findViewById(R.id.lastName);
        layout_lastName = findViewById(R.id.layout_lastName);
        input_email = findViewById(R.id.email);
        layout_email = findViewById(R.id.layout_email);
        input_password = findViewById(R.id.password);
        layout_password = findViewById(R.id.layout_password);
        input_confirmPassword = findViewById(R.id.confirmPassword);
        layout_confirmPassword = findViewById(R.id.layout_confirmPassword);


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

        //first name validations
        input_firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String FirstName = s.toString();

                if (FirstName.isEmpty()) {
                    layout_firstName.setError("Field can't be empty");
                } else {
                    layout_firstName.setError(null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //last name validations
        input_lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String lastName = s.toString();

                if (lastName.isEmpty()) {
                    layout_lastName.setError("Field can't be empty");
                } else {
                    layout_lastName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //email validations

        input_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = s.toString();

                if (email.isEmpty()) {
                    layout_email.setError("Field can't be empty");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    layout_email.setError("Please enter a valid email address");
                } else {
                    layout_email.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //password validations
        input_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = s.toString();

                if (password.isEmpty()) {
                    layout_password.setError("Field can't be empty");
                } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
                    layout_password.setError("Password is too weak");
                } else {
                    layout_password.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //confirm password validations
        // Add a TextWatcher to input_confirmPassword using addOnEditTextAttachedListener
        input_confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String confirmPassword = s.toString();
                String password = input_password.getText().toString();

                if (confirmPassword.isEmpty()) {
                    layout_confirmPassword.setError("Field can't be empty");
                } else if (!confirmPassword.equals(password)) {
                    layout_confirmPassword.setError("Passwords do not match");
                } else {
                    layout_confirmPassword.setError(null);
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