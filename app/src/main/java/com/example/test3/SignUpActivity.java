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

    private TextInputLayout layoutEmail;
    private TextInputLayout layoutFirstName;
    private TextInputLayout layoutlastName;

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

        TextInputLayout firstNameLayout = findViewById(R.id.layout_firstName);
        TextInputLayout lastNameLayout = findViewById(R.id.layout_lastName);
        TextInputLayout emailLayout = findViewById(R.id.layout_email);
        TextInputLayout passwordLayout = findViewById(R.id.layout_password);
        TextInputLayout confirmPasswordLayout = findViewById(R.id.layout_confirmPassword);

        TextInputEditText lastNaneEditText = (TextInputEditText) lastNameLayout.getEditText();
        TextInputEditText firstNameEditText = (TextInputEditText) firstNameLayout.getEditText();
        TextInputEditText emailEditText = (TextInputEditText) emailLayout.getEditText();
        TextInputEditText passwordEditText = (TextInputEditText) passwordLayout.getEditText();
        TextInputEditText confirmPasswordEditText = (TextInputEditText) confirmPasswordLayout.getEditText();

        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNaneEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        // Validate name
        if (firstName.isEmpty()) {
            firstNameLayout.setError("First Name is required");
            return;
        } else {
            firstNameLayout.setError(null);
        }

        // Validate last name
        if (lastName.isEmpty()) {
            lastNameLayout.setError("Last Name is required");
            return;
        } else {
            lastNameLayout.setError(null);
        }

        // Validate email
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            emailLayout.setError("Invalid email address");
            return;
        } else {
            emailLayout.setError(null);
        }

        // Validate password
        if (password.isEmpty()) {
            passwordLayout.setError("Password is required");
            return;
        } else if (password.length() < 8) {
            passwordLayout.setError("Password must be at least 8 characters");
            return;
        } else {
            passwordLayout.setError(null);
        }

        // Validate confirm password
        if (!confirmPassword.equals(password)) {
            confirmPasswordLayout.setError("Passwords do not match");
            return;
        } else {
            confirmPasswordLayout.setError(null);
        }

    Intent intent = new Intent(this, HomeActivity.class);

    startActivity(intent);

}
    public void goToSignInPage(){

        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}