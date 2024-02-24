package com.example.foodplanner.screens.sharedMainActivity.search.Ingredients.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.favMeals.FavouriteLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.example.foodplanner.screens.sharedMainActivity.search.Ingredients.Presenter.IngredientMealsPresenterImp;
import java.util.ArrayList;

public class SearchByIngredient extends AppCompatActivity implements IngredientMealsView {

    SpecificIngredientMealsAdaptor specificIngredientMealsAdaptor;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MealsRepositoryImpl mealsRepository;
    ProductRemoteDataSourceImpl prductRemoteDataSource;
    FavouriteLocalDataSource prodcutsLocalDataSource;
    PlannedLocalDataSourceImpl plannedLocalDataSource;
    IngredientMealsPresenterImp ingredientMealsPresenterImp;
    EditText searchByIngredient;
    Button btnSearchByIng;
    String ingredient;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_meals_of_ingredient);
        btnSearchByIng= findViewById(R.id.btn_search_in_ing);
        searchByIngredient=findViewById(R.id.ed_search_by);

        prductRemoteDataSource = new ProductRemoteDataSourceImpl();
        prodcutsLocalDataSource = new FavouriteLocalDataSource(this);
        plannedLocalDataSource = new PlannedLocalDataSourceImpl(this);
        mealsRepository = MealsRepositoryImpl.getInstance(prductRemoteDataSource, prodcutsLocalDataSource, plannedLocalDataSource);
        ingredientMealsPresenterImp= new IngredientMealsPresenterImp(mealsRepository,this);


        btnSearchByIng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredient= searchByIngredient.getText().toString().trim();
                ingredientMealsPresenterImp.getIngredientMeals(ingredient);
            }
        });

        recyclerView = findViewById(R.id.recycle_ing_meal);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void showData(ArrayList<Meal> meals) {
        specificIngredientMealsAdaptor = new SpecificIngredientMealsAdaptor(this, meals);
        recyclerView.setAdapter(specificIngredientMealsAdaptor);
    }

    @Override
    public void showErrMsg(String error) {
        Toast.makeText(this, "Sorry we can not load it as " + error, Toast.LENGTH_SHORT).show();
    }
}

