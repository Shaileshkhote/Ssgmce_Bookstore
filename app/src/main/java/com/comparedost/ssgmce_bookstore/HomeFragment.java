package com.comparedost.ssgmce_bookstore;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    SliderView sliderView;
    private SliderAdapter adapter;
    Button shopall;

    private RecyclerView recyclerView,categoryrecycler;
              List<EditorChoiceListItem> listitems;
    private  ArrayList<CategoryListItem> catlistitems;
             EditorChoiceAdapter e_c_adapter;



    private int categoryimage[]={R.drawable.profile,R.drawable.profile,R.drawable.profile,R.drawable.profile,R.drawable.profile};
    private  String categoryname[]={"IT ","CSE ","ENTC ","EE ","MECH"};

    private FirebaseFirestore fstore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View view= inflater.inflate(R.layout.fragment_home, container, false);
     recyclerView=view.findViewById(R.id.e_c_recycleview);
     categoryrecycler=view.findViewById(R.id.categories_recycleview);
     listitems=new ArrayList<>();
     catlistitems=new ArrayList<>();

        sliderView = view.findViewById(R.id.imageSlider);
        shopall=view.findViewById(R.id.shopall);
        fstore=FirebaseFirestore.getInstance();

        CategoriesAdapter catadapter=new CategoriesAdapter(getContext(),catlistitems);
        categoryrecycler.setAdapter(catadapter);

        Firebaserecycle();

        shopall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ShopAllFragment()).commit();

            }
        });



        adapter = new SliderAdapter(getContext());
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();




        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });
        renewItems(sliderView);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
     recyclerView.setItemAnimator(new DefaultItemAnimator());

        categoryrecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        categoryrecycler.setItemAnimator(new DefaultItemAnimator());
        for(int i=0;i<categoryimage.length;i++)
        {

            CategoryListItem catlist=new CategoryListItem();

            catlist.setCategoryimage(categoryimage[i]);
            catlist.setCategoryname(categoryname[i]);

            catlistitems.add(catlist);

        }












    return view;
    }
    public void renewItems(View view) {
        List<SliderItem> sliderItemList = new ArrayList<>();
        //dummy data
        for (int i = 0; i < 5; i++) {
            SliderItem sliderItem = new SliderItem();

            if (i % 2 == 0) {
                sliderItem.setImageUrl("https://firebasestorage.googleapis.com/v0/b/bookstore-app-cfaac.appspot.com/o/rise_india_educationref_1500.jpg?alt=media&token=4a3e4656-3db3-45ad-8486-97cad2b85af3");
            }

            else {
                sliderItem.setImageUrl("https://firebasestorage.googleapis.com/v0/b/bookstore-app-cfaac.appspot.com/o/Fiction%2Bbanner.jpg?alt=media&token=88abdfa3-3c34-4a10-9d12-1e023c968597");
            }
            sliderItemList.add(sliderItem);
        }
        adapter.renewItems(sliderItemList);
    }

    public void Firebaserecycle(){


        Query query=FirebaseFirestore.getInstance()
                .collection("Products");

        FirestoreRecyclerOptions<EditorChoiceListItem> options= new FirestoreRecyclerOptions.Builder<EditorChoiceListItem>()
                .setQuery(query,EditorChoiceListItem.class)
                .build();

        e_c_adapter=new EditorChoiceAdapter(options);
        recyclerView.setAdapter(e_c_adapter);
        e_c_adapter.startListening();
    }
}