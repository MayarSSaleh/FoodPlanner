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
    AreaAdapter areaAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    EditText selectByAreaName;
    MealsRepositoryImpl mealsRepository;
    ProductRemoteDataSourceImpl productRemoteDataSource;
    FaviourtLocalDataSource prodcutsLocalDataSource;
    PlannedLocalDataSourceImpl plannedLocalDataSource;
    AreaPresenterImp areaPresenterImp;
    ArrayList<Area> orignialArea;
    ProgressBar progressBar;


    public AreaActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_and_list_area);
        progressBar= findViewById(R.id.progressBar_mainAreaScreen);

        productRemoteDataSource = new ProductRemoteDataSourceImpl();
        plannedLocalDataSource = new PlannedLocalDataSourceImpl(this);
        productRemoteDataSource = new ProductRemoteDataSourceImpl();
        mealsRepository = MealsRepositoryImpl.getInstance(productRemoteDataSource, prodcutsLocalDataSource, plannedLocalDataSource);
        areaPresenterImp = new AreaPresenterImp(mealsRepository, this);
        areaPresenterImp.getAreas();
        progressBar.setVisibility( View.VISIBLE);

        recyclerView = findViewById(R.id.recycle_areas);
        selectByAreaName = findViewById(R.id.ed_areaNameinSearch);
        linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        selectByAreaName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Area> filtereddArea = filterAreas(s.toString());
                areaAdapter.setList(filtereddArea);
                areaAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private List<Area> filterAreas(String searchTerm) {
        List<Area> filteredArea = new ArrayList<>();
        if (orignialArea != null) {
            for (Area area : orignialArea) {
                if (area.getStrArea() != null && area.getStrArea().toLowerCase().contains(searchTerm.toLowerCase())) {
                    filteredArea.add(area);
                }
            }
        }
        return filteredArea;
    }

    @Override
    public void showData(ArrayList<Area> areaArrayList) {
        progressBar.setVisibility( View.GONE);
        orignialArea= new ArrayList<>(areaArrayList);
        areaAdapter = new AreaAdapter(this, areaArrayList);
        recyclerView.setAdapter(areaAdapter);
    }

    @Override
    public void showErrMsg(String error) {
        Toast.makeText(AreaActivity.this, "Sorry,we can not load for you this page as: " + error, Toast.LENGTH_SHORT).show();
    }
}