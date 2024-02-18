package com.example.foodplanner.screens.sharedMainActivity.search.Area.View;

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
import com.example.foodplanner.data.model.Area;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.example.foodplanner.screens.sharedMainActivity.search.Area.Presenter.AreaPresenterImp;

import java.util.ArrayList;
import java.util.List;

public class AreaActivity extends AppCompatActivity implements AreaView {
    private AreaAdapter areaAdapter;
    private RecyclerView recyclerView;
    private EditText selectByAreaName;
    private ProgressBar progressBar;
    private ArrayList<Area> originalArea;
    MealsRepositoryImpl mealsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_and_list_area);
        progressBar = findViewById(R.id.progressBar_mainAreaScreen);
        recyclerView = findViewById(R.id.recycle_areas);
        selectByAreaName = findViewById(R.id.ed_areaNameinSearch);

        mealsRepository = MealsRepositoryImpl.getInstance(new ProductRemoteDataSourceImpl(),
                new FaviourtLocalDataSource(this), new PlannedLocalDataSourceImpl(this));
        AreaPresenterImp areaPresenterImp = new AreaPresenterImp(mealsRepository, this);
        areaPresenterImp.getAreas();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        progressBar.setVisibility(View.VISIBLE);

        selectByAreaName.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (areaAdapter != null) {
                    areaAdapter.getFilter().filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void showData(ArrayList<Area> areaArrayList) {
        originalArea = new ArrayList<>(areaArrayList);
        areaAdapter = new AreaAdapter(this, areaArrayList);
        recyclerView.setAdapter(areaAdapter);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrMsg(String error) {
        Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show();
    }
}

