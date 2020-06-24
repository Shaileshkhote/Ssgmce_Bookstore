package com.comparedost.ssgmce_bookstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.viewHolder> {

    Context context;
    ArrayList<CategoryListItem> categoryarray;

    public CategoriesAdapter(Context context,ArrayList<CategoryListItem> categoryarray){
        this.context=context;
        this.categoryarray=categoryarray;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.categories_listitem,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.categoryImage.setImageResource(categoryarray.get(position).getCategoryimage());
        holder.categoryName.setText(categoryarray.get(position).getCategoryname());

    }

    @Override
    public int getItemCount() {
        return categoryarray.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private ImageView categoryImage;
        private TextView categoryName;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage=itemView.findViewById(R.id.categoryimage);
            categoryName=itemView.findViewById(R.id.categoryname);
        }
    }
}
