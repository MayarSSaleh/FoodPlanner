package com.example.foodplanner.screens.sharedMainActivity.search.Categry.View;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.model.Category;
import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.example.foodplanner.screens.Card.presenter.MealCardPresenterImp;
import com.example.foodplanner.screens.sharedMainActivity.search.Categry.Presenter.CategoryMealsPresenterImp;
import com.example.foodplanner.screens.sharedMainActivity.search.Categry.Presenter.CategoryPresenterImp;

import java.util.ArrayList;
import java.util.List;

// every screen has itis presenter
public class CategoryMealsActivity extends AppCompatActivity implements CategoryMealsView {

    SpecificCategoryMealsAdaptor specificCategoryMealsAdaptor;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MealsRepositoryImpl mealsRepository;
    ProductRemoteDataSourceImpl prductRemoteDataSource;
    FaviourtLocalDataSource prodcutsLocalDataSource;
    PlannedLocalDataSourceImpl plannedLocalDataSource;
    CategoryMealsPresenterImp categoryMealsPresenterImp;
    CategoryMealsView categoryMealsView;
    ProgressBar progressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_category_meals);
        progressBar=findViewById(R.id.progressBar);
        prductRemoteDataSource = new ProductRemoteDataSourceImpl();
        prodcutsLocalDataSource = new FaviourtLocalDataSource(this);
        plannedLocalDataSource = new PlannedLocalDataSourceImpl(this);
        mealsRepository = MealsRepositoryImpl.getInstance(prductRemoteDataSource, prodcutsLocalDataSource, plannedLocalDataSource);
        categoryMealsPresenterImp = new CategoryMealsPresenterImp(mealsRepository, this);

        Intent intent = getIntent();
        if (intent.hasExtra("categoryName")) {
            String categoryName = intent.getStringExtra("categoryName");
            categoryMealsPresenterImp.getCategoryMeals(categoryName);
            progressBar.setVisibility(View.VISIBLE);
        }

        recyclerView = findViewById(R.id.all_category_meals);
        linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void showData(ArrayList<MealCard> categories) {
        progressBar.setVisibility(View.GONE);
        specificCategoryMealsAdaptor = new SpecificCategoryMealsAdaptor(this, categories);
        recyclerView.setAdapter(specificCategoryMealsAdaptor);
    }

    @Override
    public void showErrMsg(String error) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "check Your internet connection"+ error, Toast.LENGTH_SHORT).show();
    }
}
