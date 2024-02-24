package com.example.foodplanner.screens.sharedMainActivity.search.Area.View;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.favMeals.FavouriteLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.model.Area;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.example.foodplanner.screens.sharedMainActivity.search.Area.Presenter.AreaPresenterImp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AreaActivity extends AppCompatActivity implements AreaView, Filterable {

    private AreaAdapter areaAdapter;
    private RecyclerView recyclerView;
    private EditText selectByAreaName;
    private ProgressBar progressBar;
    MealsRepositoryImpl mealsRepository;
    ArrayList<Area> allAreaList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_and_list_area);
        progressBar = findViewById(R.id.progressBar_mainAreaScreen);
        recyclerView = findViewById(R.id.recycle_areas);
        selectByAreaName = findViewById(R.id.ed_areaNameinSearch);

        mealsRepository = MealsRepositoryImpl.getInstance(new ProductRemoteDataSourceImpl(),
                new FavouriteLocalDataSource(this), new PlannedLocalDataSourceImpl(this));
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
                    getFilter().filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void showData(ArrayList<Area> areaArrayList) {
        areaAdapter = new AreaAdapter(this, areaArrayList);
        allAreaList = new ArrayList<>(areaArrayList);
        recyclerView.setAdapter(areaAdapter);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Area> filteredList = allAreaList.stream()
                    .filter(area -> constraint.toString().isEmpty() ||
                            area.getStrArea().toLowerCase().contains(constraint.toString().toLowerCase()))
                    .collect(Collectors.toList());
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            areaAdapter.setAreaList((List<Area>) results.values);
            areaAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public void showErrMsg(String error) {
        Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show();
    }
}

