package com.example.test3;
//This class is to change password
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
//-----------------------------------IM/2020/004 - Bimindu Aberathna---------------------------------------------
public class NewPasswordActivity extends AppCompatActivity {
    EditText current_password, new_password, confirm_password;//To store values in components in XML file
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
        // assign components into variables
        current_password = findViewById(R.id.current_password);
        new_password = findViewById(R.id.new_password);
        confirm_password = findViewById(R.id.confirm_password);
        confirm = findViewById(R.id.btn_change);
        userClass userClassObj = userClass.getInstance();//create an instance of user class to access user data
        this.loggedInUserId = userClassObj.getLoggedInUserId();//Get current user's id
        Toast.makeText(NewPasswordActivity.this, "Current user1: "+userClassObj.getUserById(loggedInUserId).getFirstName(), Toast.LENGTH_SHORT).show();
        setOnKeyListenerForNewPassword();
        setOnClickListenerForConfirmButton();
    }

    private void setOnKeyListenerForNewPassword() {//To run when user make keyboard inputs
        new_password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String regex = "^[a-zA-Z0-9]{8,}$";//regex pattern to compare user inputs
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

    private void setOnClickListenerForConfirmButton() {//To run when user make keyboard inputs
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current_password.getText().toString().equals("")){//If current password field is empty
                    current_password.setError("Enter your current password");
                    return;
                }
                if (new_password.getText().toString().equals("")){//If new password field is empty
                    new_password.setError("Enter a new password");
                    return;
                }
                if(confirm_password.getText().toString().equals("")){//if comfirm password field is empty
                    confirm_password.setError("Confirm the new password");
                    return;
                }
                if(!confirm_password.getText().toString().equals(new_password.getText().toString())){//if new password not equals to confirm password
                    confirm_password.setError("Not matching to new password");
                    return;
                }
                if(valid_password){
                    userClass userClassObj = userClass.getInstance();//create userClass instance
                    if (loggedInUserId != -1) {
                        User user = userClassObj.getUserById(loggedInUserId);
                        Toast.makeText(NewPasswordActivity.this, "Current user: "+userClassObj.getUserById(loggedInUserId).getFirstName(), Toast.LENGTH_SHORT).show();
                        if (user != null) {
                            String password = user.getPassword();
                            if(password.equals(current_password.getText().toString())){// if current password match user's password
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
    public void goToHomePage(){//redirect to home page
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}