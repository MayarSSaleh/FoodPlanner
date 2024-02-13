package com.example.foodplanner.screens.sharedMainActivity.plan.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.APPDataBase;
import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSourceImpl;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMealsDAO;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.example.foodplanner.screens.sharedMainActivity.plan.presenter.PlanMealsPresenterImp;

import java.util.ArrayList;
import java.util.List;

public class PlanFragment extends Fragment {

    WeekAdapter weekAdapter;
    List<PlannedMeals> allMeals;
    RecyclerView recyclerView;
    PlanMealsPresenterImp presenterImp;
    LinearLayoutManager linearLayoutManager;

    public PlanFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        APPDataBase db = APPDataBase.getInstance(getContext());
        PlannedMealsDAO dao = db.getPlannedMealsDAO();
        presenterImp = new PlanMealsPresenterImp();
        allMeals = new ArrayList<>();
        PlanMealsPresenterImp presenterImp = new PlanMealsPresenterImp();
        LiveData<List<PlannedMeals>> savedMeals = dao.getAllPlannedMeals();
        savedMeals.observe(this, new Observer<List<PlannedMeals>>() {
            @Override
            public void onChanged(List<PlannedMeals> lifeSavedMeals) {
//                Log.d("TAG", "in change" + allMeals.size());
                allMeals = lifeSavedMeals;
//                Log.d("TAG", "in change lifeSavedMeals" + lifeSavedMeals.size());
//                Log.d("TAG", "on all meals in plan frag" + allMeals.size());
                Log.d("TAG", "on creation of plan fragment" + presenterImp.CollectMealsAcoordingDay(allMeals).size());
                weekAdapter = new WeekAdapter(getContext(), presenterImp.CollectMealsAcoordingDay(allMeals));
                recyclerView.setAdapter(weekAdapter);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.plan, container, false);
        recyclerView = rootView.findViewById(R.id.plan_recyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        return rootView;
    }

}