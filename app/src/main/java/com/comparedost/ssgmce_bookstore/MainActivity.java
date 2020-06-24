package com.comparedost.ssgmce_bookstore;



import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {
    private final static int ID_HOME = 1;
    private final static int ID_STATEWISE = 2;
    private final static int ID_CARE = 3;
    private final static int ID_INFO = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        MeowBottomNavigation bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_STATEWISE, R.drawable.categories_icon));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_CARE, R.drawable.ic_baseline_shopping_cart_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_INFO, R.drawable.profile_icon));


//        bottomNavigation.setCount(ID_NOTIFICATION, "115");

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Fragment fragment=null;
                String name;
                switch (item.getId()) {
                    case ID_HOME :
                        fragment= new HomeFragment();
                        break;

                    case ID_CARE :
                        fragment= new CategoriesFragment();
                        break;
                    case ID_INFO :
                        fragment= new CartFragment();
                        break;
                    case ID_STATEWISE :
                        fragment= new ProfileFragment();
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

                    case ID_CARE :
                        fragment= new CategoriesFragment();
                        break;
                    case ID_INFO :
                        fragment= new CartFragment();
                        break;
                    case ID_STATEWISE :
                        fragment= new ProfileFragment();
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