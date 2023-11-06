package com.example.test3;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.test3.Model.User;
import java.util.List;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
   /*IM-2020-025  Naduni Rabel - creating a regular expression for the password pattern
   * at least 1 digit, 1 lower case, 1 upper case, letters, 1 special character, no spaces,
   * at least 4 characters*/
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +
                    //"(?=.*[a-z])" +
                    //"(?=.*[A-Z])" +
                    "(?=.*[a-zA-Z])" +
                    "(?=.*[@#$%^&+=])" +
                    "(?=\\S+$)" +
                    ".{4,}" +
                    "$");
    /*IM-2020-025  Naduni Rabel - creating references to the login button, signup textview,
    edittext for email and password*/
    private Button btn_logIn;
    private TextView txt_signUp;
    private EditText txt_email;
    private EditText txt_password;
    /*IM-2020-025 Naduni Rabel*/
    User matchedUser = null;
    /*IM-2020-025 Naduni Rabel - Alert dialog builder to show error messages upon validations */
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //creating references to the elements
        btn_logIn = findViewById(R.id.btn_change);
        txt_signUp = findViewById(R.id.txt_signup);
        txt_email = findViewById(R.id.email);
        txt_password = findViewById(R.id.password);

        ConstraintLayout parent = findViewById(R.id.pt);
        parent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
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

    public void btnLogin(){

            String input_email = txt_email.getText().toString().trim();
            String input_password = txt_password.getText().toString().trim();
            userClass userClassObj = userClass.getInstance();

            List<User> list = userClassObj.getList();
            if(!input_email.isEmpty() && !input_password.isEmpty()) {
                if(validateEmail() & validatePassword()) {
                    for (User user : list) {
                        if (user.getEmail().equals(input_email)) {
                            matchedUser = user;
                            break;
                        }
                    }
                    if (matchedUser != null) {
                        String userPwd = matchedUser.getPassword();
                        if (userPwd.equals(input_password)) {


                            int loggedInUserId = matchedUser.getId(); // Assuming getId() returns the user's ID

                            // Store the logged-in user's ID in userClass (or any other data storage method)
                            userClassObj.setLoggedInUserId(loggedInUserId);
                            Intent intent = new Intent(this, HomeActivity.class);
                            startActivity(intent);



                        }
                        else {
                            //password
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


    }

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
    public void goToSignUpPage(){
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
    private boolean validateEmail(){
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
}

