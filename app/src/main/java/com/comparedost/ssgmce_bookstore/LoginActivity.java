package com.comparedost.ssgmce_bookstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    Button callSignUp, login_btn;
    //    ImageView image;
//    TextView logoText, sloganText;
    TextInputLayout username, password;
    TextInputEditText passwordedit, usernameedit;
    String usernamefromedittext, passwordfromedt;
    FirebaseAuth mauth;
    FirebaseUser CurrentUser;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState); //This Line will hide the status bar from the screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        passwordedit = findViewById(R.id.PasswordEditText);
        usernameedit = findViewById(R.id.Login);
        login_btn = findViewById(R.id.login_btn);
        callSignUp = findViewById(R.id.callSignUp);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        final Loading_Dialog dialog=new Loading_Dialog().newInstance();




        mauth = FirebaseAuth.getInstance();
        CurrentUser = mauth.getCurrentUser();

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                usernamefromedittext = usernameedit.getText().toString();
                passwordfromedt = passwordedit.getText().toString();

                if ((usernamefromedittext.isEmpty())) {


                    username.setError("Username Needed");

                }
                if ((passwordfromedt.isEmpty())) {
                    password.setError("Password Needed");
                }

                if ((!(usernamefromedittext.isEmpty()) && (!(passwordfromedt.isEmpty())))) {
                    dialog.show(LoginActivity.this.getSupportFragmentManager(),"");

                    mauth.signInWithEmailAndPassword(usernamefromedittext, passwordfromedt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                if (mauth.getCurrentUser().isEmailVerified()) {
                                    dialog.dismiss();
                                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(i);
                                } else {
                                    dialog.dismiss();
                                    Toast.makeText(LoginActivity.this, "Please Verify Email ", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                dialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }

    public void onStart() {
        super.onStart();

        if (CurrentUser != null) {
            if (CurrentUser.isEmailVerified()) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }

        }
    }
}
