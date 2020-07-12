package com.comparedost.ssgmce_bookstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShopallAdapter extends FirestoreRecyclerAdapter<EditorChoiceListItem,ShopallAdapter.EditorChoiceViewHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public  ShopallAdapter(@NonNull FirestoreRecyclerOptions<EditorChoiceListItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull EditorChoiceViewHolder holder, int position, @NonNull EditorChoiceListItem model) {
        try {
            Picasso.get().load(model.getPhotoURL()).into(holder.productImage);
            holder.ProductName.setText(model.getBook_Title());
            holder.Branch.setText(model.getBook_Author());
            holder.Semester.setText(model.getBook_Edition());
            holder.SellingPrice.setText(model.getSelling_Price());
            holder.OrignalPrice.setText(model.getOrignal_Price());

        }catch (NullPointerException e){}


    }

    @NonNull
    @Override
    public EditorChoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shopallitem, parent, false);

        return new EditorChoiceViewHolder(view) ;
    }

    public class EditorChoiceViewHolder extends RecyclerView.ViewHolder{
        ImageView productImage;
        TextView ProductName,Branch,Semester,OrignalPrice,SellingPrice;
        public EditorChoiceViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.e_c_productimage);
            ProductName=itemView.findViewById(R.id.e_c_product_name);
            Branch=itemView.findViewById(R.id.e_c_branch_name);
            Semester=itemView.findViewById(R.id.e_c_semester);
            OrignalPrice=itemView.findViewById(R.id.e_c_orignalprice);
            SellingPrice=itemView.findViewById(R.id.e_c_offerprice);
        }
    }






}
