package com.comparedost.ssgmce_bookstore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private RecyclerView cartItemsRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cart, container, false);

        cartItemsRecyclerView=view.findViewById(R.id.cart_item_reclycleview);

        LinearLayoutManager LayoutManager= new LinearLayoutManager(getContext());
        LayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        List<CartItemModel> cartItemModelList= new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0,R.drawable.product_image,"Aditya TOC IT (6th sem)",1,"Rs.100/-"));
        cartItemModelList.add(new CartItemModel(0,R.drawable.product_image,"Aditya DBMS IT (6th sem)",2,"Rs.200/-"));
        cartItemModelList.add(new CartItemModel(0,R.drawable.product_image,"Aditya POM IT (6th sem)",2,"Rs.200/-"));
        cartItemModelList.add(new CartItemModel(1,"Price (3 items)","Rs.300/-","free","Rs.300/-","Rs.150/-"));


        CartAdapter cartAdapter=new CartAdapter(cartItemModelList);
        cartItemsRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        return view;
    }
}