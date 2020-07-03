package com.comparedost.ssgmce_bookstore;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {
    private final static int ID_HOME = 1;
    private final static int ID_CATEGORIES = 2;
    private final static int ID_CART = 3;
    private final static int ID_PROFILE = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        MeowBottomNavigation bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_CATEGORIES, R.drawable.categories_icon));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_CART, R.drawable.ic_baseline_shopping_cart_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_PROFILE, R.drawable.profile_icon));

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Fragment fragment=null;
                String name;
                switch (item.getId()) {
                    case ID_HOME :
                        fragment= new HomeFragment();
                        break;

                    case ID_CART :

                        fragment= new CartFragment();
                        break;

                    case ID_PROFILE :

                        fragment= new ProfileFragment();
                        break;
                    case ID_CATEGORIES :
                        fragment= new CategoriesFragment();
                        break;


                    default:
                        name = "";
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();

            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                Fragment fragment=null;
                String name;
                switch (item.getId()) {
                    case ID_HOME :
                        fragment= new HomeFragment();
                        break;

                    case ID_CART :

                        fragment= new CartFragment();
                        break;

                    case ID_PROFILE :

                        fragment= new ProfileFragment();
                        break;
                    case ID_CATEGORIES :
                        fragment= new CategoriesFragment();
                        break;


                    default:
                        name = "";
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();


            }

        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });

        bottomNavigation.setCount(ID_HOME, "115");

        bottomNavigation.show(ID_HOME,true);
    }
}