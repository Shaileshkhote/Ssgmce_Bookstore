package com.comparedost.ssgmce_bookstore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.picasso.Picasso;

public class Product_Screen extends AppCompatActivity {

    TextView textView4,textView20,e_c_orignalprice,textView11,textView21,textView22,textView23,textView24,textView25,textView5;
    ImageView share,wishlist,imageButton2;
    Button Add_to_cart;
    String userid,docRef;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__screen);
        textView20=findViewById(R.id.textView20);
        textView4=findViewById(R.id.textView4);
        e_c_orignalprice=findViewById(R.id.e_c_orignalprice);
        textView11=findViewById(R.id.textView11);
        textView21=findViewById(R.id.textView21);
        textView20=findViewById(R.id.textView20);
        textView22=findViewById(R.id.textView22);
        textView23=findViewById(R.id.textView23);
        textView24 =findViewById(R.id.textView24);
                textView25=findViewById(R.id.textView25);
        textView5=findViewById(R.id.textView5);
        share=findViewById(R.id.share);
        wishlist=findViewById(R.id.wishlist);
        Add_to_cart=findViewById(R.id.Add_to_cart);
        imageButton2=findViewById(R.id.imageButton2);

        Intent i=getIntent();
        Bundle extras=i.getExtras();
        if(extras!=null)
        {
            docRef=extras.getString("docRef");
            Toast.makeText(this, "docRef"+docRef, Toast.LENGTH_SHORT).show();

        }

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Product_Screen.this, "Shared", Toast.LENGTH_SHORT).show();
            }
        });


        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Product_Screen.this, "Added to wishlist", Toast.LENGTH_SHORT).show();
            }
        });

        Add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Product_Screen.this, "Added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        fauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        userid=fauth.getCurrentUser().getUid();

        DocumentReference documentReference =fstore.collection("Products").document(docRef);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                Picasso.get().load(documentSnapshot.getString("PhotoURL")).into(imageButton2);
                textView4.setText(documentSnapshot.getString("Selling_Price"));
                e_c_orignalprice.setText(documentSnapshot.getString("Orignal_Price"));
                textView20.setText(documentSnapshot.getString("Book_Title"));
                textView11.setText(documentSnapshot.getString("Book_Condition"));
                textView21.setText(documentSnapshot.getString("Book_Author"));
                textView22.setText(documentSnapshot.getString("Book_Condition"));
                 textView23.setText(documentSnapshot.getString("Book_Description"));
                 textView24.setText(documentSnapshot.getString("Orignal_Price"));
                textView25.setText(documentSnapshot.getString("Selling_Price"));
                textView5.setText(documentSnapshot.getString("Book_Title"));


            }
        });

    }
}