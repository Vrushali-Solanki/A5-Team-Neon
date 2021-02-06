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

import static com.google.firebase.database.FirebaseDatabase.getInstance;

public class PatientRegisterActivity extends AppCompatActivity {

    private EditText InputPhoneNumber,InputChildName,InputParentName,InputBirth,InputBloodgroup,InputPassword,InputConfirmPassword;
    private Button PatientRegister;
    private TextView reginfo;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);

        PatientRegister=(Button)findViewById(R.id.pt_reg_btn);
        InputParentName=(EditText)findViewById(R.id.pt_p_reg_name);
        InputBloodgroup=(EditText)findViewById(R.id.c_blood);
        InputBirth=(EditText)findViewById(R.id.pt_birth);
        InputChildName=(EditText)findViewById(R.id.pt_c_reg_name);
        InputPhoneNumber=(EditText)findViewById(R.id.pt_reg_phone_number);
        InputPassword=(EditText)findViewById(R.id.pt_reg_pass);
        InputConfirmPassword=(EditText)findViewById(R.id.pt_reg_conf_pass);
        loadingBar=new ProgressDialog(this);
        reginfo=(TextView) findViewById(R.id.pt_reg_tvInfo);

        PatientRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                CreateAccount();
            }
        });
        reginfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PatientRegisterActivity.this,PatientLoginActivity.class);
                startActivity(intent);
            }
        });
    }


    private void CreateAccount() {

        String cname=InputChildName.getText().toString();
        String pname=InputParentName.getText().toString();
        String phone=InputPhoneNumber.getText().toString();
        String password=InputPassword.getText().toString();

        if(TextUtils.isEmpty(cname)){

            Toast.makeText(this,"Please write your name",Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(pname)){

            Toast.makeText(this,"Please write your name",Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(phone)){

            Toast.makeText(this,"Please write your phone number",Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(password)){

            Toast.makeText(this,"Please write your password",Toast.LENGTH_SHORT).show();
        }

        else{
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatePhonenumber(cname,pname,phone,password);
        }


    }

    private void ValidatePhonenumber(String cname, String pname,String phone, String password)
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
                        userDataMap.put("pname",pname);
                        userDataMap.put("cname",cname);

                        RootRef.child("Users").child(phone).updateChildren(userDataMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(PatientRegisterActivity.this,"Congratulations,your account is created!",Toast.LENGTH_SHORT).show();
                                            loadingBar.dismiss();
                                            Intent intent=new Intent(PatientRegisterActivity.this,PatientLoginActivity.class);
                                            startActivity(intent);
                                        }
                                        else {
                                            loadingBar.dismiss();
                                            Toast.makeText(PatientRegisterActivity.this,"network error!",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }
                    else{
                        Toast.makeText(PatientRegisterActivity.this,"This "+phone+"  number already exists.",Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                        Toast.makeText(PatientRegisterActivity.this,"Please try using another phone number",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(PatientRegisterActivity.this,MainActivity.class);
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