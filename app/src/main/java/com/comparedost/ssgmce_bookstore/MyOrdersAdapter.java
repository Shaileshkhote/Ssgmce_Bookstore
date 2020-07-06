package com.comparedost.ssgmce_bookstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.viewHolder> {

    Context context;
    ArrayList<MyOrdersListItem> orderList;

    public MyOrdersAdapter(Context context, ArrayList<MyOrdersListItem> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.activity_my_orders,parent,false);

        viewHolder viewholder=new viewHolder(view);
        return viewholder;
    }


    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.book_img.setImageResource(orderList.get(position).getImage());
        holder.Book_name.setText(orderList.get(position).getBook_name());
        holder.date_details.setText(orderList.get(position).getDate());
        holder.order_id.setText(orderList.get(position).getOrder_id());
        holder.invoice_btn.setText(orderList.get(position).getInvoice_btn());



    }

    @Override
    public int getItemCount() {


        return orderList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{
        Button invoice_btn;
        ImageView book_img;
        TextView Book_name,date_details,order_id;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            book_img=itemView.findViewById(R.id.book_img);
            Book_name=itemView.findViewById(R.id.Book_name);
            date_details=itemView.findViewById(R.id.date_details);
            order_id=itemView.findViewById(R.id.order_id);
            invoice_btn=itemView.findViewById(R.id.invoice_btn);
        }
    }
}