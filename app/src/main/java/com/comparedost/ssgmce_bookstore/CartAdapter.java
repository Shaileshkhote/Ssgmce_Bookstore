package com.comparedost.ssgmce_bookstore;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;




public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    ArrayList<CartItemModel> mcartlist;

    public CartAdapter(ArrayList<CartItemModel> cartlist) {

        this.mcartlist = cartlist;

        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle;
        private TextView productPrice;
        private TextView productQuantity;

        public ViewHolder(View itemView) {

            super(itemView);

            productImage=itemView.findViewById(R.id.product_image);
            productTitle=itemView.findViewById(R.id.product_title);
            productPrice=itemView.findViewById(R.id.product_price);
            productQuantity=itemView.findViewById(R.id.product_quantity);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CartItemModel model = mcartlist.get(position);

        try {
            Picasso.get().load(model.getPhotoURL()).into(holder.productImage);
            holder.productTitle.setText(model.getBook_Title());

            holder.productPrice.setText(model.getSelling_Price());
            holder.productQuantity.setText("1");

        }catch (NullPointerException e){}

    }

    @Override
    public int getItemCount() {

        return mcartlist.size();
    }


}
