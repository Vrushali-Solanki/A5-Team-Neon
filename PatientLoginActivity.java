package com.example.childvaccine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientLoginActivity extends AppCompatActivity {
    private EditText InputPhoneNumber, InputPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;
    private TextView AdminLink, NotAdminLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        LoginButton = (Button) findViewById(R.id.login_pt_button);
        InputPhoneNumber = (EditText) findViewById(R.id.login_pt_phone);
        InputPassword = (EditText) findViewById(R.id.login_pt_password);
        AdminLink = (TextView) findViewById(R.id.login_doc);
        NotAdminLink = (TextView) findViewById(R.id.log_not_doctor);
        loadingBar = new ProgressDialog(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginUser();
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginButton.setText("Login Doctor");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                Intent intent=new Intent(PatientLoginActivity.this,DoctorRegisterActivity.class);
                startActivity(intent);
            }
        });

        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginButton.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
            }
        });
    }
    private void LoginUser () {
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();


        if (TextUtils.isEmpty(phone)) {

            Toast.makeText(this, "Please write your phone number", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(password)) {

            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();

        }
        else {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait while we are checking credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

        }

    }
}