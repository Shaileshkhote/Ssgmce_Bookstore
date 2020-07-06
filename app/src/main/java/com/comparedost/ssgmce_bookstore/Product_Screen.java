package com.comparedost.ssgmce_bookstore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Product_Screen extends AppCompatActivity {

    TextView textView20;
    String userid;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__screen);
        textView20=findViewById(R.id.textView20);

        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        userid=fauth.getCurrentUser().getUid();

        DocumentReference documentReference =fstore.collection("Users").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                textView20.setText(documentSnapshot.getString("Book_Title"));
            }
        });

    }
}