package com.example.foodplanner.screens.Card.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.APPDataBase;
import com.example.foodplanner.data.local_db.favMeals.FavouriteLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMealsDAO;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.example.foodplanner.screens.Card.presenter.MealCardPresenterImp;

import java.util.List;

public class ChossePlannedDay extends AppCompatActivity {
    private static final String TAG = "TAG";
    TextView saturday, sunday, monday, tuesday, wednesday, thursday, friday;
    MealCardPresenterImp mealCardPresenterImp;
    Meal planeThisMeal;
    MealCardView mealCardView;
    MealsRepositoryImpl repository;
    ProductRemoteDataSourceImpl productRemoteDataSource;
    FavouriteLocalDataSource prodcutsLocalDataSource;
    PlannedLocalDataSourceImpl plannedLocalDataSource;
    List<PlannedMeals> allPlannedmeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_the_day);
        userInterface();
        setOnClickListenersForDays();
        productRemoteDataSource = new ProductRemoteDataSourceImpl();
        prodcutsLocalDataSource = new FavouriteLocalDataSource(this);
        plannedLocalDataSource = new PlannedLocalDataSourceImpl(this);
        repository = MealsRepositoryImpl.getInstance(productRemoteDataSource, prodcutsLocalDataSource, plannedLocalDataSource);
        mealCardPresenterImp = new MealCardPresenterImp(mealCardView, repository, ChossePlannedDay.this);

        Intent intent = getIntent();
        planeThisMeal = (Meal) intent.getSerializableExtra("myObject");

        APPDataBase db = APPDataBase.getInstance(ChossePlannedDay.this);
        PlannedMealsDAO dao = db.getPlannedMealsDAO();
        LiveData<List<PlannedMeals>> savedMeals = dao.getAllPlannedMeals();
        savedMeals.observe(this, new Observer<List<PlannedMeals>>() {
            @Override
            public void onChanged(List<PlannedMeals> lifeSavedMeals) {
                allPlannedmeals = lifeSavedMeals;
            }
        });
    }

    private void userInterface(){
        saturday = findViewById(R.id.tv_saturday);
        sunday = findViewById(R.id.tv_sunday);
        monday = findViewById(R.id.tv_monday);
        tuesday = findViewById(R.id.tv_tuesday);
        wednesday = findViewById(R.id.tv_wednesday);
        thursday = findViewById(R.id.tv_thursday);
        friday = findViewById(R.id.tv_friday);
    }

    private void setOnClickListenersForDays(){
        saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCardPresenterImp.addToPlan(allPlannedmeals, planeThisMeal, "Saturday");
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCardPresenterImp.addToPlan(allPlannedmeals, planeThisMeal, "Sunday");
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCardPresenterImp.addToPlan(allPlannedmeals, planeThisMeal, "Monday");
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_SHORT).show();
                mealCardPresenterImp.addToPlan(allPlannedmeals, planeThisMeal, "Tuesday");
                finish();
            }
        });

        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCardPresenterImp.addToPlan(allPlannedmeals, planeThisMeal, "Wednesday");
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCardPresenterImp.addToPlan(allPlannedmeals, planeThisMeal, "Thursday");
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCardPresenterImp.addToPlan(allPlannedmeals, planeThisMeal, friday.getText().toString());
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
