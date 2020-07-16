package com.comparedost.ssgmce_bookstore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class CartFragment extends Fragment {

    private FirebaseFirestore fstore;
    private FirebaseDatabase  mydb;
    private FirebaseAuth mauth;

    String Currentuser;


    ArrayList<CartItemModel> list = new ArrayList<>();




    RecyclerView.Adapter adapter;

    RecyclerView cartrecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cart, container, false);

        cartrecyclerView=view.findViewById(R.id.cartrecycler);
        mauth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        Currentuser=mauth.getCurrentUser().getUid();
        mydb=FirebaseDatabase.getInstance();
        DatabaseReference   myref=mydb.getReference("Users").child(Currentuser);
        adapter=new CartAdapter(list);

        cartrecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        cartrecyclerView.setItemAnimator(new DefaultItemAnimator());
        cartrecyclerView.addItemDecoration(new DividerItemDecoration(cartrecyclerView.getContext(), DividerItemDecoration.VERTICAL));



        try{

            myref.child("My_Cart").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for(DataSnapshot  d:dataSnapshot.getChildren()){

                        String id=d.getKey();
                        Log.e(TAG, "onEvent: "+ id);

                         DocumentReference documentReference =fstore.collection("Products").document(id);

                        documentReference.addSnapshotListener( new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                                String Booktitle=documentSnapshot.getString("Book_Title" );

                                Log.e(TAG, "onEvent: "+ Booktitle);
                                CartItemModel obj=new CartItemModel();


                                obj.setBook_Title(documentSnapshot.getString("Book_Title"));
                                obj.setPhotoURL(documentSnapshot.getString("PhotoURL"));
                                obj.setSelling_Price(documentSnapshot.getString("Selling_Price"));

                                list.add(obj);

                                cartrecyclerView.setAdapter(adapter);



                            }
                        });


                        }



                    }



                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }




            });

        }catch (NullPointerException e){}





        return view;
    }
}