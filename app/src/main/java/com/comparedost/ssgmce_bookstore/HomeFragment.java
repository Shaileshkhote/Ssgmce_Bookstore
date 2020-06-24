package com.comparedost.ssgmce_bookstore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView,categoryrecycler;
    private  ArrayList<EditorChoiceListItem> listitems;
    private  ArrayList<CategoryListItem> catlistitems;
    private int productimage []={R.drawable.bookstore,R.drawable.bookstore,R.drawable.bookstore,R.drawable.bookstore,R.drawable.bookstore};
    private String prodname []={"Aditya Dbms","Aditya Dbms","Aditya Dbms","Aditya Dbms","Aditya Dbms"};
    private String semester[]={"Sem-5","Sem-5","Sem-5","Sem-5","Sem-5"};
    private  String branch[]={"I.T","I.T","I.T","I.T","I.T"};
    private String orignalprice[]={"150","150","150","150","150"};
    private String offerprice[]={"100","100","100","100","100"};

    private int categoryimage[]={R.drawable.profile,R.drawable.profile,R.drawable.profile,R.drawable.profile,R.drawable.profile};
    private  String categoryname[]={"IT Branch","CSE Branch","ENTC Branch","EE Branch","MECH Branch"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View view= inflater.inflate(R.layout.fragment_home, container, false);
     recyclerView=view.findViewById(R.id.e_c_recycleview);
     categoryrecycler=view.findViewById(R.id.categories_recycleview);
     listitems=new ArrayList<>();
     catlistitems=new ArrayList<>();

     recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
     recyclerView.setItemAnimator(new DefaultItemAnimator());

        categoryrecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        categoryrecycler.setItemAnimator(new DefaultItemAnimator());


        for(int i=0;i<productimage.length;i++)
     {
         EditorChoiceListItem item=new EditorChoiceListItem();
         CategoryListItem catlist=new CategoryListItem();
         item.setProductview(productimage[i]);
         item.setProductname(prodname[i]);
         item.setSemester(semester[i]);
         item.setBranch(branch[i]);
         item.setOrignamlprice(orignalprice[i]);
         item.setOfferprice(offerprice[i]);

         catlist.setCategoryimage(categoryimage[i]);
         catlist.setCategoryname(categoryname[i]);

         catlistitems.add(catlist);
         listitems.add(item);
     }

     EditorChoiceAdapter adapter=new EditorChoiceAdapter(getContext(),listitems);

     CategoriesAdapter catadapter=new CategoriesAdapter(getContext(),catlistitems);
     categoryrecycler.setAdapter(catadapter);
     recyclerView.setAdapter(adapter);

    return view;
    }
}