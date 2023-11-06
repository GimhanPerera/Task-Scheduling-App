package com.example.test3;
//IM/2020/049 - Naduni
//import static com.example.test3.userClass.userDetails;


import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.test3.Model.User;
import java.util.List;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    //IM/2020/049 - Naduni
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

    User matchedUser = null;

    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI elements
        btn_logIn = findViewById(R.id.btn_confirm);
        txt_signUp = findViewById(R.id.txt_signup);
        txt_email = findViewById(R.id.email);
        txt_password = findViewById(R.id.password);
        ConstraintLayout parent = findViewById(R.id.pt);
        parent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {//Set no error msg
                txt_email.setError(null);
                txt_password.setError(null);
                return false;
            }
        });
        btn_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLogin();
            }
        });
       /** txt_email.setOnKeyListener(new View.OnKeyListener() {
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
        });**/
        txt_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUpPage();
            }
        });

    }

    //IM/2020/049 - Naduni
    public void btnLogin(){

            String input_email = txt_email.getText().toString().trim();//get user email
            String input_password = txt_password.getText().toString().trim();//get user pwd
            userClass userClassObj = userClass.getInstance();

            List<User> list = userClassObj.getList();//Get user details list
            if(!input_email.isEmpty() && !input_password.isEmpty()) {//check email & pwd are empty or not
                if(validateEmail() & validatePassword()) {//Check validation
                    for (User user : list) {//Check email
                        if (user.getEmail().equals(input_email)) {
                            matchedUser = user;
                            break;
                        }
                    }
                    if (matchedUser != null) {  //if emailis match with existing user
                        String userPwd = matchedUser.getPassword();
                        if (userPwd.equals(input_password)) {//Check pwd
                            //if pwd is correct

                            int loggedInUserId = matchedUser.getId(); // get user ID

                            userClassObj.setLoggedInUserId(loggedInUserId);//Set user ID as logged in user id
                            Intent intent = new Intent(this, HomeActivity.class);//Open home page
                            startActivity(intent);
                        }
                        else {//if password incorrect
                            showAlertDialog("Incorrect Password", "The password is incorrect.");
                         }

                    }
                    else{
                    //invalid username or password
                        showAlertDialog("Invalid Credentials", "Email not found.");
                    }
                }
                else{
                    showAlertDialog("Invalid Input", "Please enter valid email and password.");
                }
            }
            else{
              //  cannot be empty
                showAlertDialog("Empty Fields", "Email and password cannot be empty.");
            }
        //IM/2020/049 - Naduni
    }

    //IM/2020/049 - Naduni
    //Show alert dialog box
    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Dismiss the dialog
                    }
                })
                .show();
    }

    public void goToSignUpPage(){//Open signup page
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
    private boolean validateEmail(){//Email validations
       String input_email = txt_email.getText().toString().trim();

       if(input_email.isEmpty()){
          // txt_email.setError("Field cannot be empty");
           return false;
       } else if(!Patterns.EMAIL_ADDRESS.matcher(input_email).matches()){
           txt_email.setError("Invalid email address");
       } else{
          // txt_email.setError(null);
           return true;
       }
       return false;
    }
    private boolean validatePassword(){
        String input_password = txt_password.getText().toString().trim();

        if(input_password.isEmpty()){
           // txt_password.setError("Field cannot be empty");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(input_password).matches()){
            txt_password.setError("Invalid Password");
        } else{
          //  txt_password.setError(null);
            return true;
        }
        return false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Hide the keyboard when the user touches outside the text areas
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        return super.onTouchEvent(event);
    }
}
//IM/2020/049 - Naduni

