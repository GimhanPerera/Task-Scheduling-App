//IM/2020/049 - Gimhan
package com.example.test3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.*;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);

        button=findViewById(R.id.btn_getstarted);
        button.setOnClickListener(new View.OnClickListener() {//Get started btn onClick Listener
            @Override
            public void onClick(View v) {
                btnGetStarted();
            }
        });
    }

    //Open login Activity
    public void btnGetStarted(){
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

}
//IM/2020/049 - Gimhan