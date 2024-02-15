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
import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMealsDAO;
import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.example.foodplanner.screens.Card.presenter.MealCardPresenterImp;

import java.util.List;

public class ChossePlannedDay extends AppCompatActivity {
    private static final String TAG = "TAG";
    TextView sta, sun, mon, tues, wed, thu, friday;
    MealCardPresenterImp mealCardPresenterImp;
    MealCard mealCard;
    MealCardView mealCardView;
    MealsRepositoryImpl repository;
    ProductRemoteDataSourceImpl productRemoteDataSource;
    FaviourtLocalDataSource prodcutsLocalDataSource;
    PlannedLocalDataSourceImpl plannedLocalDataSource;
    String choosedDay;
    List<PlannedMeals> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fra_choose_the_day);
        userInterface();
        setOnClickListenersForDays();
        productRemoteDataSource = new ProductRemoteDataSourceImpl();
        prodcutsLocalDataSource = new FaviourtLocalDataSource(this);
        plannedLocalDataSource = new PlannedLocalDataSourceImpl(this);
        repository = MealsRepositoryImpl.getInstance(productRemoteDataSource, prodcutsLocalDataSource, plannedLocalDataSource);
        mealCardPresenterImp = new MealCardPresenterImp(mealCardView, repository, ChossePlannedDay.this);

        Intent intent = getIntent();
        mealCard = (MealCard) intent.getSerializableExtra("myObject");

        APPDataBase db = APPDataBase.getInstance(ChossePlannedDay.this);
        PlannedMealsDAO dao = db.getPlannedMealsDAO();
        LiveData<List<PlannedMeals>> savedMeals = dao.getAllPlannedMeals();
        savedMeals.observe(this, new Observer<List<PlannedMeals>>() {
            @Override
            public void onChanged(List<PlannedMeals> lifeSavedMeals) {
                meals = lifeSavedMeals;
            }
        });
    }








    private void userInterface(){
        sta = findViewById(R.id.tv_saturday);
        sun = findViewById(R.id.tv_suunday);
        mon = findViewById(R.id.tv_monday);
        tues = findViewById(R.id.tv_tuesday);
        wed = findViewById(R.id.tv_wednesday);
        thu = findViewById(R.id.tv_thursday);
        friday = findViewById(R.id.tv_friday);
    }












    private void setOnClickListenersForDays(){
        sta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCardPresenterImp.addToPlan(meals, mealCard, "Sunday");
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_LONG).show();
                finish();
                Log.i(TAG, "onClick: of Saturday choose planed day ");
            }
        });
        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCardPresenterImp.addToPlan(meals, mealCard, "Sunday");
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCardPresenterImp.addToPlan(meals, mealCard, "Monday");
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        tues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_LONG).show();
                mealCardPresenterImp.addToPlan(meals, mealCard, "Tuesday");
                finish();
            }
        });
        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCardPresenterImp.addToPlan(meals, mealCard, "Wednesday");
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCardPresenterImp.addToPlan(meals, mealCard, "Thursday");
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCardPresenterImp.addToPlan(meals, mealCard, friday.getText().toString());
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}
