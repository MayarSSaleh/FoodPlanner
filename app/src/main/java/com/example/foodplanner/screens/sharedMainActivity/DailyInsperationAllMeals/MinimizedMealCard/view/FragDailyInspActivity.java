package com.example.foodplanner.screens.sharedMainActivity.DailyInsperationAllMeals.MinimizedMealCard.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.screens.sharedMainActivity.DailyInsperationAllMeals.MinimizedMealCard.presenter.InsperMealsPresenter;


public class FragDailyInspActivity extends Fragment {
    TextView dailyInspi;
    RecyclerView recyclerView;
    private static final String TAG = "team";
    MiniMealsCardAdaptor miniMealsCardAdaptor;
    LinearLayoutManager linearLayoutManager;
    InsperMealsPresenter insperMealsPresenter;

    public FragDailyInspActivity() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frag_daily_insp_activity, container, false);
        recyclerView = rootView.findViewById(R.id.daily_insp_recycle);
        // give him the viewer and the reposirty to get the data form it
//
//      insperMealsPresenter = new InsperMealsPresenter(MealsRepository.getInstance(new ProductRemoteDataSourceImpl(), new ProdcutsLocalDataSourceImpl(this)));
//        insperMealsPresenter.getAllProducts();
//        linearLayoutManager = new LinearLayoutManager(this);
//        miniMealsCardAdaptor = new MiniMealsCardAdaptor(fragDailyInspActivity.this, mealsList);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(miniMealsCardAdaptor);
//        Log.i(TAG, "on creation");
        return rootView;
    }
}
