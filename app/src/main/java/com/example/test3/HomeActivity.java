package com.example.test3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem item1 = menu.findItem(R.id.Home);
        MenuItem item2 = menu.findItem(R.id.Notification);
        MenuItem item3 = menu.findItem(R.id.Profile);

        //--------------Initiate the list-----------------
        //-------------------------------

        //item1.setTitle(item2.getTitle());
        //String s= item1.getItemId();
        //bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if(item.getTitle()==item1.getTitle()){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                }
                else if(item.getTitle()==item2.getTitle()){
                    //getSupportFragmentManager().beginTransaction().replace(R.id.container,addTaskFragment).commit();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container,addTaskFragment);
                    fragmentTransaction.commit();
                }
                else if(item.getTitle()==item3.getTitle()){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment).commit();
                }


                /*switch (item.getItemId()){
                    case R.id.Home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        break;
                    case R.id.Notification:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,notificationFragment).commit();
                        break;
                    case R.id.Profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment).commit();
                        break;
                }*/

                        return false;
                    }
                });
    }

    public void openAddPage(){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,addTaskFragment).commit();
    }
    public void openHomePage(){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
    }
}