package com.example.foodplanner.screens.sharedMainActivity.favMeals.favList.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.APPDataBase;
import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.favMeals.FavMealsDAO;
import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSourceImpl;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.example.foodplanner.screens.sharedMainActivity.favMeals.favList.presenter.FavMealsPresnterImp;

import java.util.ArrayList;
import java.util.List;

public class FragFavMealsActivity extends Fragment {
    private static final String TAG = "team";
    FavMiniMealsCardAdaptor miniMealsCardAdaptor;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MealsRepositoryImpl repository;
    ProductRemoteDataSourceImpl productRemoteDataSource;
    FaviourtLocalDataSourceImpl prodcutsLocalDataSource;
    PlannedLocalDataSourceImpl plannedLocalDataSource;
    List<FavMeals> favMealsList;
    FavMealsPresnterImp presenterImp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fav_meals, container, false);
        recyclerView = rootView.findViewById(R.id.fav_recycle);

        productRemoteDataSource = new ProductRemoteDataSourceImpl();
        prodcutsLocalDataSource = new FaviourtLocalDataSourceImpl(requireContext());
        plannedLocalDataSource = new PlannedLocalDataSourceImpl(requireContext());
        repository = MealsRepositoryImpl.getInstance(productRemoteDataSource, prodcutsLocalDataSource, plannedLocalDataSource);
        linearLayoutManager = new LinearLayoutManager(getContext());
        favMealsList = new ArrayList<>();
        miniMealsCardAdaptor = new FavMiniMealsCardAdaptor(getContext(), favMealsList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(miniMealsCardAdaptor);
        Log.i(TAG, "on creation of fav");
        presenterImp = new FavMealsPresnterImp(repository);

        APPDataBase db = APPDataBase.getInstance(getContext());
        FavMealsDAO dao = db.getFavMealsDAO();
        LiveData<List<FavMeals>> Favourits = dao.getAllFavProducts();
        Favourits.observe(getViewLifecycleOwner(), new Observer<List<FavMeals>>() {
            @Override
            public void onChanged(List<FavMeals> products) {
                Log.i(TAG, "onChanged: in fav activity");
                miniMealsCardAdaptor.setList(products);
            }
        });
        return rootView;
    }
}


