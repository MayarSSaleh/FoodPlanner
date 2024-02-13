package com.example.foodplanner.screens.sharedMainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.APPDataBase;
import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.favMeals.FavMealsDAO;
import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.screens.Card.view.MealCardActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainScreenActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MyViewPageAdapter myViewPageAdapter;
    ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ui();
        handleFragmentChoose();

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: profile");
                Intent intent = new Intent(MainScreenActivity.this, MealCardActivity.class);
                startActivity(intent);
            }
        });

//  to test the insertion in data base before make button add fav set on action<<<<<<<<<
        APPDataBase db = APPDataBase.getInstance(this);
        FavMealsDAO dao = db.getFavMealsDAO();
//         same photo of first prodcut but it is not the first it is just test
//        FavMeals testDB = new FavMeals();
//        testDB.setName("TEST");
//        testDB.setMealId("12345");
//        testDB.setPhotourl("https://cdn.dummyjson.com/product-images/1/thumbnail.jpg");
//        testDB.setVideoUrl("https://www.youtube.com/watch?v=mTvlmY4vCug");
//        testDB.setCountry("egypt");
//        testDB.setFav(true);
//        List<Ingredient> in=new ArrayList<>();
//        in.add(new Ingredient("ing1","ing2"));
//        testDB.setAllingredient(in);
//        testDB.setSteps("one");
//        Log.i(TAG, "testDB "+testDB.getSteps());
//
//        new Thread() {
//            @Override
//            public void run() {
//                Log.i(TAG, "run: insert prodcut");
//                dao.insertToFav(testDB);
//            }
//        }.start();
    }
    private void ui() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);
        myViewPageAdapter = new MyViewPageAdapter(this);
        viewPager2.setAdapter(myViewPageAdapter);
        profile = findViewById(R.id.profile);
    }

    private void handleFragmentChoose() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        // to connect the tablayout in the body view with the taps
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });


    }
}