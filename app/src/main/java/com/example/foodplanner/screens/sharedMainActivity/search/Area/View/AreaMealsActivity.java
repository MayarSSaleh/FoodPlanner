package com.example.foodplanner.screens.sharedMainActivity.search.Area.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.example.foodplanner.screens.sharedMainActivity.search.Area.Presenter.AreaMealsPresenterImp;
import java.util.ArrayList;

public class AreaMealsActivity extends AppCompatActivity implements AreaMealsView {

    AreaMealsAdapter areaMealsAdapter;
    RecyclerView recyclerView;
    MealsRepositoryImpl mealsRepository;
    ProductRemoteDataSourceImpl prductRemoteDataSource;
    FaviourtLocalDataSource prodcutsLocalDataSource;
    PlannedLocalDataSourceImpl plannedLocalDataSource;
    AreaMealsPresenterImp areaMealsPresenterImp;
    ProgressBar progressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_area_meals);
        progressBar=findViewById(R.id.progressBarInIngredientMeals);
        prductRemoteDataSource = new ProductRemoteDataSourceImpl();
        prodcutsLocalDataSource = new FaviourtLocalDataSource(this);
        plannedLocalDataSource = new PlannedLocalDataSourceImpl(this);
        mealsRepository = MealsRepositoryImpl.getInstance(prductRemoteDataSource, prodcutsLocalDataSource, plannedLocalDataSource);
        areaMealsPresenterImp = new AreaMealsPresenterImp(mealsRepository, this);

        Intent intent = getIntent();
        if (intent.hasExtra("areaName")) {
            String areaName = intent.getStringExtra("areaName");
            areaMealsPresenterImp.getAreaMeals(areaName);
            progressBar.setVisibility(View.VISIBLE);
        }

        recyclerView = findViewById(R.id.recycle_ing_meal);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void showData(ArrayList<MealCard> categories) {
        progressBar.setVisibility(View.GONE);
        areaMealsAdapter = new AreaMealsAdapter(this, categories);
        recyclerView.setAdapter(areaMealsAdapter);
    }

    @Override
    public void showErrMsg(String error) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Sorry, we can not get it as "+ error, Toast.LENGTH_SHORT).show();
    }
}
