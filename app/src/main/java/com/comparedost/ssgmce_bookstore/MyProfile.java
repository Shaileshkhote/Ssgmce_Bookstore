package com.comparedost.ssgmce_bookstore;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyProfile extends AppCompatActivity {
    TextView number1,email,name,userid;


    private String phonefromdb, uidfromdb,emailfromdb,namefromdb;

    private FirebaseDatabase mydb;
    private DatabaseReference myref;
    private FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        number1 = (TextView) findViewById(R.id.phone_textview);
        email=(TextView) findViewById(R.id.email_textview);
        name=(TextView) findViewById(R.id.name);
        userid=(TextView) findViewById(R.id.id_textview) ;

//
        mauth = FirebaseAuth.getInstance();
        mydb = FirebaseDatabase.getInstance();
        myref = mydb.getReference("Users");

        uidfromdb = mauth.getCurrentUser().getUid();
        DatabaseReference myref2 = myref.child(uidfromdb);
        myref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                emailfromdb =dataSnapshot.child("Email").getValue(String.class);
                phonefromdb =dataSnapshot.child("Phone").getValue(String.class);
                uidfromdb      =dataSnapshot.child("Userid").getValue(String.class);
                namefromdb=dataSnapshot.child("Name").getValue(String.class);

                number1.setText(phonefromdb);
                email.setText(emailfromdb);
                userid.setText(uidfromdb);
                name.setText(namefromdb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MyProfile.this, "Database Error", Toast.LENGTH_SHORT).show();
            }

        });

    }
}