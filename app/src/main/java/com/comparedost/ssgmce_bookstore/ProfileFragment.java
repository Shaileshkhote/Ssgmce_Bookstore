package com.comparedost.ssgmce_bookstore;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {
    TextView username,number;

    private String UID,usernamefromdb,phonefromdb;

    private FirebaseDatabase mydb;
    private DatabaseReference myref;
    private FirebaseAuth mauth;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


      View view=inflater.inflate(R.layout.fragment_profile, container, false);

      username=view.findViewById(R.id.username);
      number=view.findViewById(R.id.number);

      mauth=FirebaseAuth.getInstance();
      mydb=FirebaseDatabase.getInstance();
      myref=mydb.getReference("Users");


      UID=mauth.getCurrentUser().getUid();
       DatabaseReference myref2=myref.child(UID);

       myref2.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               usernamefromdb=dataSnapshot.child("Name").getValue(String.class);
               phonefromdb=dataSnapshot.child("Phone").getValue(String.class);

               username.setText(usernamefromdb);
               number.setText(phonefromdb);

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
               Toast.makeText(getContext(), "Database Error", Toast.LENGTH_SHORT).show();


           }
       });




        return view;

    }
}