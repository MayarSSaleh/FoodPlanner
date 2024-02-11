package com.example.foodplanner.screens.Card.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSourceImpl;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.example.foodplanner.screens.Card.presenter.MealCardPresenter;
import com.example.foodplanner.screens.Card.presenter.MealCardPresenterImp;

public class ChossePlannedDay extends AppCompatActivity {
    private static final String TAG = "TAG";
    TextView sta, sun, mon, tues, wed, thu, friday;
    MealCardPresenterImp mealCardPresenterImp ;
    MealCard mealCard;
    MealCardView mealCardView;
    MealsRepositoryImpl repository;
    ProductRemoteDataSourceImpl productRemoteDataSource;
    FaviourtLocalDataSourceImpl prodcutsLocalDataSource;
    PlannedLocalDataSourceImpl plannedLocalDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fra_choose_the_day);
        productRemoteDataSource= new ProductRemoteDataSourceImpl();
        prodcutsLocalDataSource = new FaviourtLocalDataSourceImpl(this);
        plannedLocalDataSource = new PlannedLocalDataSourceImpl(this);
        repository=MealsRepositoryImpl.getInstance(productRemoteDataSource, prodcutsLocalDataSource, plannedLocalDataSource);
        mealCardPresenterImp = new MealCardPresenterImp(mealCardView,repository);
        sta = findViewById(R.id.tv_saturday);
        sun = findViewById(R.id.tv_suunday);
        mon = findViewById(R.id.tv_monday);
        tues = findViewById(R.id.tv_tuesday);
        wed = findViewById(R.id.tv_wednesday);
        thu = findViewById(R.id.tv_thursday);
        friday = findViewById(R.id.tv_friday);
        Intent intent = getIntent();
        mealCard = (MealCard) intent.getSerializableExtra("myObject");

        sta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCardPresenterImp.addToPlan(mealCard, "Saturday");
//                Log.i(TAG, "on creation of fav" + sta.getText().toString());
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCardPresenterImp.addToPlan(mealCard, "Sunday");
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCardPresenterImp.addToPlan(mealCard, "Monday");
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        tues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_LONG).show();
                mealCardPresenterImp.addToPlan(mealCard, "Tuesday");
                finish();
            }
        });
        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCardPresenterImp.addToPlan(mealCard, "Wednesday");
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCardPresenterImp.addToPlan(mealCard, "Thursday");
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealCardPresenterImp.addToPlan(mealCard, friday.getText().toString());
                Toast.makeText(ChossePlannedDay.this, "The meal added to the plan", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
