package com.example.childvaccine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class VaccineAvailability extends AppCompatActivity {

    ListView lvProgram;
    String[] programName = {"Polio","ChickenPox","Hepatitis-A","Hepatitis-B","Rotavirus","Influenza"};
    String[] programDescription = {"2/02/2019","2/02/2019","3/02/2019","5/02/2019","5/02/2019","12/02/2019"};
    int [] programImages = {R.drawable.polio,R.drawable.chickenpox,R.drawable.ha,R.drawable.hb,R.drawable.rv,R.drawable.influenza};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_availability);

        lvProgram = findViewById(R.id.lvProgram);
        com.example.myapplication.ProgramAdapter programAdapter = new com.example.myapplication.ProgramAdapter(this, programName, programImages, programDescription);
        lvProgram.setAdapter(programAdapter);
    }
}