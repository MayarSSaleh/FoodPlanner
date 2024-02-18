package com.example.foodplanner.screens.sharedMainActivity.search.Categry.View;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.model.Category;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.example.foodplanner.screens.sharedMainActivity.search.Area.View.AreaActivity;
import com.example.foodplanner.screens.sharedMainActivity.search.Categry.Presenter.CategoryPresenterImp;

import java.util.ArrayList;
import java.util.List;

public class CategoryListActivity extends AppCompatActivity implements CategoryView {

    CategoryAdapter categoryAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    CategoryPresenterImp categoryPresenter;
    MealsRepositoryImpl mealsRepository;
    ProductRemoteDataSourceImpl productRemoteDataSource;
    FaviourtLocalDataSource prodcutsLocalDataSource;
    PlannedLocalDataSourceImpl plannedLocalDataSource;
    EditText et_searchbyCategory;
    List<Category> originalCategories;
    ProgressBar progressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_and_list_categ);
        et_searchbyCategory = findViewById(R.id.et_searchbyCategory);
        progressBar = findViewById(R.id.progressBar2);
        productRemoteDataSource = new ProductRemoteDataSourceImpl();
        plannedLocalDataSource = new PlannedLocalDataSourceImpl(this);
        productRemoteDataSource = new ProductRemoteDataSourceImpl();
        progressBar.setVisibility(View.VISIBLE);

        mealsRepository = MealsRepositoryImpl.getInstance(productRemoteDataSource, prodcutsLocalDataSource, plannedLocalDataSource);
        recyclerView = findViewById(R.id.recycle_categ);
        linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        // Create presenter with repository and view
        categoryPresenter = new CategoryPresenterImp(mealsRepository, this);
        // Fetch categories
        categoryPresenter.getAllCategories();

        et_searchbyCategory.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Category> filteredCategories = filterCategories(s.toString());
                categoryAdapter.setList(filteredCategories);
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void showData(ArrayList<Category> categories) {
        progressBar.setVisibility(View.GONE);
        originalCategories = new ArrayList<>(categories);
        categoryAdapter = new CategoryAdapter(this, categories);
        recyclerView.setAdapter(categoryAdapter);
    }


    @Override
    public void showErrMsg(String error) {
        Toast.makeText(this, "Sorry,we can not load for you this page as: " + error, Toast.LENGTH_SHORT).show();

        progressBar.setVisibility(View.GONE);
    }

    private List<Category> filterCategories(String letter) {
        List<Category> filteredCategories = new ArrayList<>();
        for (Category category : originalCategories) {
            if (category.getStrCategory().toLowerCase().contains(letter.toLowerCase())) {
                filteredCategories.add(category);
            }
        }
        return filteredCategories;
    }

}
