package com.comparedost.ssgmce_bookstore;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import static android.widget.LinearLayout.*;

public class MyOrders extends AppCompatActivity {
    Button invoice_btn;
    RecyclerView recyclerView;
    MyOrdersAdapter adapter;
    ArrayList<MyOrdersListItem> orderList;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders_adapter_recycler_view);

        invoice_btn=findViewById(R.id.invoice_btn);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orderList=new ArrayList<>();

        orderList.add(new MyOrdersListItem(R.drawable.bookstore,
                "Aditya Publication DBMS",
                "Order ID - 304795",
                "Successfuly Paid on Mon,15 June,2020",
                "Download Invoice"));

        orderList.add(new MyOrdersListItem(R.drawable.bookstore,
                "Aditya Publication POM",
                "Order ID - 304796",
                "Successfuly Paid on Mon,15 June,2020",
                "Download Invoice"));

        orderList.add(new MyOrdersListItem(R.drawable.bookstore,
                "Aditya Publication DSP",
                "Order ID - 304796",
                "Successfuly Paid on Mon,16 June,2020",
                "Download Invoice"));
        orderList.add(new MyOrdersListItem(R.drawable.bookstore,
                "Aditya Publication DBMS",
                "Order ID - 304795",
                "Successfuly Paid on Mon,15 June,2020",
                "Download Invoice"));
        orderList.add(new MyOrdersListItem(R.drawable.bookstore,
                "Aditya Publication POM",
                "Order ID - 304796",
                "Successfuly Paid on Mon,15 June,2020",
                "Download Invoice"));

        orderList.add(new MyOrdersListItem(R.drawable.bookstore,
                "Aditya Publication DSP",
                "Order ID - 304796",
                "Successfuly Paid on Mon,16 June,2020",
                "Download Invoice"));

        adapter=new MyOrdersAdapter(this,orderList);
         recyclerView.setAdapter(adapter);
    }
}