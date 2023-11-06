package com.example.test3;
// IM/2020/049 - Gimhan
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.test3.Model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    NotificationFragment notificationFragment = new NotificationFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    AddTaskFragment addTaskFragment = new AddTaskFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // IM/2020/049 - Gimhan
        //Set up the navigation bar backend
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem item1 = menu.findItem(R.id.Home);
        MenuItem item2 = menu.findItem(R.id.Notification);
        MenuItem item3 = menu.findItem(R.id.Profile);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {    //Set btn navigation
                if(item.getTitle()==item1.getTitle()){ //Open home fragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                }
                else if(item.getTitle()==item2.getTitle()){ //Open add task fragment
                    //getSupportFragmentManager().beginTransaction().replace(R.id.container,addTaskFragment).commit();
                    //FragmentManager fragmentManager = getSupportFragmentManager();
                    //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    //fragmentTransaction.replace(R.id.container,addTaskFragment);
                    //fragmentTransaction.commit();
                }
                else if(item.getTitle()==item3.getTitle()){ //Open profile fragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment).commit();
                }
                        return false;
                    }
                });
    }

    public void openAddPage(){  // Open add page
        getSupportFragmentManager().beginTransaction().replace(R.id.container,addTaskFragment).commit();
    }
    public void openHomePage(){ //Open home page
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
    }

}