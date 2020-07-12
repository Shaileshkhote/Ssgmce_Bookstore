package com.comparedost.ssgmce_bookstore;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class ShopAllFragment extends Fragment {

    private FirebaseFirestore fstore;

    ShopallAdapter shopall;

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.shopall, container, false);
        recyclerView=view.findViewById(R.id.shopallreycleview);




        fstore=FirebaseFirestore.getInstance();



        Firebaserecycle();










        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));














        return view;


    }

    public void Firebaserecycle(){


        Query query=FirebaseFirestore.getInstance()
                .collection("Products");

        FirestoreRecyclerOptions<EditorChoiceListItem> options= new FirestoreRecyclerOptions.Builder<EditorChoiceListItem>()
                .setQuery(query,EditorChoiceListItem.class)
                .build();

       shopall=new ShopallAdapter(options);
        recyclerView.setAdapter(shopall);
       shopall.startListening();
    }
}

