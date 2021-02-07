package com.example.childvaccine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomebNavActivity extends AppCompatActivity {

    BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeb_nav);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new HomeFragment()).commit();

        bnv=(BottomNavigationView)findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment temp=null;
                switch (item.getItemId()){
                    case R.id.menu_home: temp=new HomeFragment();
                    break;
                    case R.id.menu_doc_apnt: temp=new DoctorAptFragment();
                    break;
                    case R.id.menu_nutritional_chart: temp=new NutritionalFragment();
                    break;
                    case R.id.menu_settings: temp=new SettingsFragment();
                    break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,temp).commit();

                return true;
            }
        });

    }
}