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
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.test3.Model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SignUpActivity extends AppCompatActivity {
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
    private CheckBox checkBox;

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
        checkBox = findViewById(R.id.checkBox);

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

        String firstName = input_firstName.getText().toString().trim();
        String lastName = input_lastName.getText().toString().trim();
        String email = input_email.getText().toString().trim();
        String password = input_password.getText().toString().trim();
        String confirmPassword = input_confirmPassword.getText().toString().trim();

        if( !firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) {
            if (validateFirstName() & validateLastName() & validateEmail() & validatePassword() & validateConfirmPassword(password, confirmPassword) & validateCheckBox() ) {
                userClass userClassObj = userClass.getInstance();
                User newUser = new User(userClassObj.getNextId(),firstName,lastName,email,password);
                userClassObj.setLoggedInUserId(userClassObj.getNextId());
                userClassObj.setNewUser(newUser);
                listClass object=listClass.getInstance();
                object.clearAllTasks();//Clear all tasks. Bcz this is new user
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
        }else {
            if(firstName.isEmpty()){
                layout_firstName.setError("     First name is required");
            } else if (lastName.isEmpty()) {
                layout_lastName.setError("     Last name is required");
            } else if (email.isEmpty()) {
                layout_email.setError("     Email is required");
            } else if (password.isEmpty()){
                layout_password.setError("     Password is can not be empty");
            }else {
                layout_confirmPassword.setError("     Confirm password can not be empty");
            }
        }

    }

    public void goToSignInPage() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    private boolean validateFirstName() {
        String firstName = input_firstName.getText().toString().trim();

        String regexPattern = "^[a-zA-Z.' ]+$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(firstName);
        if (matcher.matches()) {
            layout_firstName.setError("");
            return true;
        } else {
            layout_firstName.setError("     Enter a valid name");
            return false;
        }
    }
    private boolean validateLastName() {

        String lastName = input_lastName.getText().toString().trim();

        String regexPattern = "^[a-zA-Z.' ]+$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(lastName);

        if (matcher.matches()){
            layout_lastName.setError("");
            return true;
        }else {
            layout_lastName.setError("     Enter a valid name");
            return false;
        }
    }
    private boolean validateEmail() {
        String email = input_email.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            layout_email.setError("     Invalid email address");
            return false;
        } else{
            layout_email.setError("");
            return true;
        }
    }
    private boolean validatePassword() {
        String password = input_password.getText().toString().trim();

        String regexPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=*]).{8,}$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(password);

        if (matcher.matches()) {
            if (password.length()>8){
                layout_password.setError("");
                return true;
            } else {
                layout_password.setError("    Password must contain at least 8 characters");
                return false;
            }
        } else{
            layout_password.setError("Password is too weak");
            return false;
        }
    }
    private boolean validateConfirmPassword(String password, String confirmPassword){
        if (password.equals(confirmPassword)){
            layout_confirmPassword.setError("");
            return true;
        }else {
            layout_confirmPassword.setError("     Passwords must be same");
            return false;
        }
    }

    private boolean validateCheckBox(){

        if (checkBox.isChecked()){
            return true;
        } else {
            return false;
        }

    }

}