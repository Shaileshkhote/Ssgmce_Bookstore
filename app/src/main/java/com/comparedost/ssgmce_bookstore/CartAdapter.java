package com.comparedost.ssgmce_bookstore;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

    private List<CartItemModel> cartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType()){

            case 0:
                  return CartItemModel.CART_ITEM;

            case 1:
                  return CartItemModel.TOTAL_AMOUNT;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){

            case CartItemModel.CART_ITEM:


            View    cartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
            return new CartItemViewholder(cartItemView);

            case CartItemModel.TOTAL_AMOUNT:


                View cartTotalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_layout_amount,parent,false);
                return new CartTotalAmountViewholder(cartTotalView);


            default:
                return null;

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch(cartItemModelList.get(position).getType()){
            case CartItemModel.CART_ITEM:
                int resource=cartItemModelList.get(position).getProductImage();
                String title=cartItemModelList.get(position).getProductTitle();
                String productPrice=cartItemModelList.get(position).getProductPrice();


                ((CartItemViewholder)holder).setItemDetails(resource,title,productPrice);


             break;
            case CartItemModel.TOTAL_AMOUNT:
            String totalItems= cartItemModelList.get(position).getTotalItems();
            String totalItemPrice= cartItemModelList.get(position).getTotalItemPrice();
                String deliveryPrice= cartItemModelList.get(position).getDeliveryPrice();
                String totalAmount= cartItemModelList.get(position).getTotalAmount();
                String savedAmount= cartItemModelList.get(position).getSavedAmount();


                ((CartTotalAmountViewholder)holder).setTotalAmount(totalItems,totalItemPrice,deliveryPrice,totalAmount,savedAmount);



                break;
            default:return;
        }

    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    class CartItemViewholder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private TextView productTitle;
        private TextView productPrice;
        private TextView productQuantity;


        public CartItemViewholder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.product_image);
            productTitle=itemView.findViewById(R.id.product_title);
            productPrice=itemView.findViewById(R.id.product_price);
            productQuantity=itemView.findViewById(R.id.product_quantity);
        }
        private void setItemDetails(int resource, String title, String productPriceText){
            productImage.setImageResource(resource);
            productTitle.setText(title);
            productPrice.setText(productPriceText);

        }


    }

    class CartTotalAmountViewholder extends RecyclerView.ViewHolder{

        private TextView totalItems;
        private TextView totalItemPrice;
        private TextView deliveryPrice;
        private TextView totalAmount;
        private TextView savedAmount;


        public CartTotalAmountViewholder(@NonNull View itemView) {
            super(itemView);

            totalItems=itemView.findViewById(R.id.total_items);
            totalItemPrice=itemView.findViewById(R.id.total_item_price);
            deliveryPrice=itemView.findViewById(R.id.delivery_charge);
            totalAmount=itemView.findViewById(R.id.total_price);
            savedAmount=itemView.findViewById(R.id.saved_amount);

        }
        private void setTotalAmount(String totalItemsText,String totalItemPriceText,String deliverPriceText,String totalAmountText,String savedAmountText){
            totalItems.setText(totalItemsText);
            totalItemPrice.setText(totalItemPriceText);
            deliveryPrice.setText(deliverPriceText);
            totalAmount.setText(totalAmountText);
            savedAmount.setText(savedAmountText);
        }
    }
}
