package com.comparedost.ssgmce_bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    TextInputEditText email,username,phoneno,name,password;
    TextInputLayout emaillout,usernamelout,phonenolout,namelout,passwordlout;
    Button Register_btn,Signin_btn;
    ProgressBar progressbar;
    private String emailstr,usernamestr,passstr,phonestr,namestr,userUID;

    private FirebaseAuth mauth;
    private FirebaseDatabase mydb;
    private DatabaseReference myref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //This Line will hide the status bar from the screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

        email=findViewById(R.id.email);
        username=findViewById(R.id.username);
        phoneno=findViewById(R.id.phone);
        name=findViewById(R.id.fullname);
        password=findViewById(R.id.password);

        emaillout=findViewById(R.id.reg_email);
        usernamelout=findViewById(R.id.reg_username);
        phonenolout=findViewById(R.id.reg_phoneNo);
        namelout=findViewById(R.id.reg_name);
        passwordlout=findViewById(R.id.reg_password);

        Register_btn=findViewById(R.id.go_btn);
        Signin_btn=findViewById(R.id.reg_log_btn);

        progressbar=findViewById(R.id.progressBar);

        mauth=FirebaseAuth.getInstance();
        mydb=FirebaseDatabase.getInstance();
        myref=mydb.getReference("Users");




        Register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailstr=email.getText().toString();
                usernamestr=username.getText().toString();
                passstr=password.getText().toString();
                namestr=name.getText().toString();
                phonestr=phoneno.getText().toString();

                if((email.getText().toString().isEmpty())){
                    emaillout.setError("Email Needed");

                }
                if((phoneno.getText().toString().isEmpty())){
                    phonenolout.setError("Phone No Needed");

                }
                if((username.getText().toString().isEmpty())){
                    usernamelout.setError("Username Needed");

                }
                if((name.getText().toString().isEmpty())){
                    namelout.setError("Name Needed");

                }
                if((password.getText().toString().isEmpty())) {
                    passwordlout.setError("Password");
                }

                            if(!(emailstr.isEmpty())  && !(usernamestr.isEmpty())  && !(phonestr.isEmpty()) &&!(namestr.isEmpty())  &&!(passstr.isEmpty())){
                                Toast.makeText(SignupActivity.this, emailstr, Toast.LENGTH_SHORT).show();
                                progressbar.setVisibility(View.VISIBLE);

                        mauth.createUserWithEmailAndPassword(emailstr,passstr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                if(authResult != null){
                                    mauth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(SignupActivity.this, "Verification Link Send", Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });

                                    UserProfileChangeRequest updateProfile=new UserProfileChangeRequest.Builder().setDisplayName((name.getText().toString())).build();
                                    mauth.getCurrentUser().updateProfile(updateProfile);
                                    userUID=mauth.getCurrentUser().getUid();

                                    myref.child(userUID).setValue(userUID).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){

                                                myref.child(userUID).child("Email").setValue(emailstr);
                                                myref.child(userUID).child("Phone").setValue(phonestr);
                                                myref.child(userUID).child("Name").setValue(namestr);
                                                myref.child(userUID).child("UserName").setValue(usernamestr).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()){
                                                            Toast.makeText(SignupActivity.this, "Registered Succesfully !!", Toast.LENGTH_SHORT).show();
                                                            progressbar.setVisibility(View.GONE);
                                                            Handler handler=new Handler();

                                                            handler.postDelayed(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    Intent i=new Intent(SignupActivity.this, LoginActivity.class);
                                                                    startActivity(i);

                                                                }
                                                            },3000);
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    });


                                }
                            }
                        });


                    }

                }

        });


    }


}
