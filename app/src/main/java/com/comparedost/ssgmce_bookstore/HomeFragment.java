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

    private RecyclerView recyclerView;
    private  ArrayList<EditorChoiceListItem> listitems;
    private int productimage []={R.drawable.bookstore,R.drawable.bookstore,R.drawable.bookstore,R.drawable.bookstore,R.drawable.bookstore};
    private String prodname []={"Aditya Dbms","Aditya Dbms","Aditya Dbms","Aditya Dbms","Aditya Dbms"};
    private String semester[]={"Sem-5","Sem-5","Sem-5","Sem-5","Sem-5"};
    private  String branch[]={"I.T","I.T","I.T","I.T","I.T"};
    private String orignalprice[]={"150","150","150","150","150"};
    private String offerprice[]={"100","100","100","100","100"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View view= inflater.inflate(R.layout.fragment_home, container, false);
     recyclerView=view.findViewById(R.id.e_c_recycleview);
     listitems=new ArrayList<>();

     recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
     recyclerView.setItemAnimator(new DefaultItemAnimator());

     for(int i=0;i<productimage.length;i++)
     {
         EditorChoiceListItem item=new EditorChoiceListItem();
         item.setProductview(productimage[i]);
         item.setProductname(prodname[i]);
         item.setSemester(semester[i]);
         item.setBranch(branch[i]);
         item.setOrignamlprice(orignalprice[i]);
         item.setOfferprice(offerprice[i]);

         listitems.add(item);
     }

     EditorChoiceAdapter adapter=new EditorChoiceAdapter(getContext(),listitems);
     recyclerView.setAdapter(adapter);

    return view;
    }
}