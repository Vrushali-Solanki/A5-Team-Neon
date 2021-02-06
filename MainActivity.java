package com.example.childvaccine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button Patient,Doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Patient=findViewById(R.id.btnpatient);
        Doctor=findViewById(R.id.btndoctor);

        Patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this ,PatientRegisterActivity.class);
                startActivity(intent);
            }
        });

        Doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this ,DoctorRegisterActivity.class);
                startActivity(intent);
            }
        });



    }
}