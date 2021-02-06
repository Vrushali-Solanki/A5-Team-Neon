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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class DoctorRegisterActivity extends AppCompatActivity {
    private EditText Inputdphone,InputdName,InputDegree,InputExp,InputdPassword,InputdConfirmPassword,Inputwork;
    private Button DoctorRegister;
    private TextView regdinfo;
    private ProgressDialog mloadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);

        DoctorRegister=(Button)findViewById(R.id.d_reg_btn);
        InputdName=(EditText)findViewById(R.id.d_reg_name);
        InputExp=(EditText)findViewById(R.id.d_reg_ex);
        InputDegree=(EditText)findViewById(R.id.d_reg_degree);
        Inputdphone=(EditText)findViewById(R.id.d_reg_phone_number);
        InputdPassword=(EditText)findViewById(R.id.d_reg_pass);
        Inputwork=(EditText)findViewById(R.id.d_work_location);
        InputdConfirmPassword=(EditText)findViewById(R.id.d_reg_conf_pass);
        mloadingBar=new ProgressDialog(this);
        regdinfo=(TextView) findViewById(R.id.d_tvInfo);

        DoctorRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                CreateAccount();
            }
        });
        regdinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DoctorRegisterActivity.this,PatientLoginActivity.class);
                startActivity(intent);
            }
        });
    }


    private void CreateAccount() {


        String dname=InputdName.getText().toString();
        String phone=Inputdphone.getText().toString();
        String password=InputdPassword.getText().toString();

        if(TextUtils.isEmpty(dname)){

            Toast.makeText(this,"Please write your name",Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(phone)){

            Toast.makeText(this,"Please write your phone number",Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(password)){

            Toast.makeText(this,"Please write your password",Toast.LENGTH_SHORT).show();
        }

        else{
            mloadingBar.setTitle("Create Account");
            mloadingBar.setMessage("Please wait while we are checking the credentials.");
            mloadingBar.setCanceledOnTouchOutside(false);
            mloadingBar.show();

            ValidatePhonenumber(dname,phone,password);
        }


    }

    private void ValidatePhonenumber(String dname,String phone, String password)
    {

        {

            final DatabaseReference RootRef;
            RootRef= FirebaseDatabase.getInstance().getReference();

            RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if(!(dataSnapshot.child("Users").child(phone).exists()))
                    {

                        HashMap<String, Object> userDataMap =new HashMap<>();
                        userDataMap.put("phone",phone);
                        userDataMap.put("password",password);
                        userDataMap.put("pname",dname);


                        RootRef.child("Users").child(phone).updateChildren(userDataMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(DoctorRegisterActivity.this,"Congratulations,your account is created!",Toast.LENGTH_SHORT).show();
                                            mloadingBar.dismiss();
                                            Intent intent=new Intent(DoctorRegisterActivity.this,PatientLoginActivity.class);
                                            startActivity(intent);
                                        }
                                        else {
                                            mloadingBar.dismiss();
                                            Toast.makeText(DoctorRegisterActivity.this,"network error!",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }
                    else{
                        Toast.makeText(DoctorRegisterActivity.this,"This "+phone+"  number already exists.",Toast.LENGTH_SHORT).show();
                        mloadingBar.dismiss();
                        Toast.makeText(DoctorRegisterActivity.this,"Please try using another phone number",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(DoctorRegisterActivity.this,MainActivity.class);
                        startActivity(intent);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }






    }

}