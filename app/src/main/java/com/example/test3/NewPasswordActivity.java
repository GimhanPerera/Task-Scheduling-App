package com.example.test3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.example.test3.Model.User;

public class NewPasswordActivity extends AppCompatActivity {
    EditText current_password, new_password, confirm_password;
    Button confirm;
    boolean valid_password = true;
    int loggedInUserId;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        current_password = findViewById(R.id.current_password);
        new_password = findViewById(R.id.new_password);
        confirm_password = findViewById(R.id.confirm_password);
        confirm = findViewById(R.id.btn_change);
        userClass userClassObj = userClass.getInstance();
        this.loggedInUserId = userClassObj.getLoggedInUserId();
        Toast.makeText(NewPasswordActivity.this, "Current user1: "+userClassObj.getUserById(loggedInUserId).getFirstName(), Toast.LENGTH_SHORT).show();
        setOnKeyListenerForNewPassword();
        setOnClickListenerForConfirmButton();
    }

    private void setOnKeyListenerForNewPassword() {
        new_password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String regex = "^[a-zA-Z0-9]{8,}$";
                String chars = new_password.getText().toString();
                // Create a Pattern object
                Pattern pattern = Pattern.compile(regex);

                // Create a Matcher object
                Matcher matcher = pattern.matcher(chars);

                // Check if the provided name matches the pattern
                if (matcher.matches()) {
                    new_password.setError(null);
                    valid_password = true;
                } else {
                    new_password.setError("Enter a valid password");
                    valid_password = false;
                }
                return false;
            }
        });
    }

    private void setOnClickListenerForConfirmButton() {
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current_password.getText().toString().equals("")){
                    current_password.setError("Enter your current password");
                    return;
                }
                if (new_password.getText().toString().equals("")){
                    new_password.setError("Enter a new password");
                    return;
                }
                if(confirm_password.getText().toString().equals("")){
                    confirm_password.setError("Confirm the new password");
                    return;
                }
                if(!confirm_password.getText().toString().equals(new_password.getText().toString())){
                    confirm_password.setError("Not matching to new password");
                    return;
                }
                if(valid_password){
                    // Add your logic here for a valid password
                    userClass userClassObj = userClass.getInstance();
                    if (loggedInUserId != -1) {
                        User user = userClassObj.getUserById(loggedInUserId);
                        Toast.makeText(NewPasswordActivity.this, "Current user: "+userClassObj.getUserById(loggedInUserId).getFirstName(), Toast.LENGTH_SHORT).show();
                        if (user != null) {
                            String password = user.getPassword();
                            if(password.equals(current_password.getText().toString())){
                                userClassObj.getUserById(loggedInUserId).setPassword(new_password.getText().toString());
                                Toast.makeText(NewPasswordActivity.this, "Password changed", Toast.LENGTH_SHORT).show();
                                goToHomePage();
                            }else{
                                Toast.makeText(NewPasswordActivity.this, "Password is wrong", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }

            }

        });
    }
    public void goToHomePage(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}