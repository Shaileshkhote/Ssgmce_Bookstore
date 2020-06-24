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

public class EditorChoiceAdapter extends RecyclerView.Adapter<EditorChoiceAdapter.ViewHolder> {
    Context context;
    ArrayList<EditorChoiceListItem> listitems;
    public EditorChoiceAdapter(Context context,ArrayList<EditorChoiceListItem> listitems){

        this.context=context;
        this.listitems=listitems;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView prodimage;
        private TextView prodname,semester,branch,orignalprice,offerprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            prodimage=itemView.findViewById(R.id.e_c_productimage);
            semester=itemView.findViewById(R.id.e_c_semester);
            branch=itemView.findViewById(R.id.e_c_branch_name);
            orignalprice=itemView.findViewById(R.id.e_c_orignalprice);
            offerprice=itemView.findViewById(R.id.e_c_offerprice);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(context).inflate(R.layout.editors_choice_listitem,parent,false);

       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            holder.prodimage.setImageResource(listitems.get(position).getProductview());
            holder.prodname.setText(listitems.get(position).getProductname());
            holder.branch.setText(listitems.get(position).getBranch());
            holder.orignalprice.setText(listitems.get(position).getOrignamlprice());
            holder.semester.setText(listitems.get(position).getSemester());
            holder.offerprice.setText(listitems.get(position).getOfferprice());

        }catch (NullPointerException e){};





    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }



}
