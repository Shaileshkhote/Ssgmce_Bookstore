package com.comparedost.ssgmce_bookstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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
    TextInputEditText passwordedit,usernameedit;
    String usernamefromedittext,passwordfromedt;
    FirebaseAuth mauth;
    FirebaseUser CurrentUser;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //This Line will hide the status bar from the screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        passwordedit=findViewById(R.id.PasswordEditText);
        usernameedit=findViewById(R.id.Login);
        login_btn = findViewById(R.id.login_btn);
        username = findViewById(R.id.username);
          password = findViewById(R.id.password);





        mauth=FirebaseAuth.getInstance();
        CurrentUser=mauth.getCurrentUser();





        login_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                usernamefromedittext=usernameedit.getText().toString();
                passwordfromedt=passwordedit.getText().toString();

                if((usernamefromedittext.isEmpty())){

                    username.setError("Username Needed");

                }
                if ((passwordfromedt.isEmpty())){
                    password.setError("Password Needed");
                }

                if((!(usernamefromedittext.isEmpty()) && (!(passwordfromedt.isEmpty())))){

                    mauth.signInWithEmailAndPassword(usernamefromedittext,passwordfromedt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent i=new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(i);

                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }

    public void onStart(){
        super.onStart();
        if (CurrentUser != null){
            Intent i=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);

        }
    }
}
