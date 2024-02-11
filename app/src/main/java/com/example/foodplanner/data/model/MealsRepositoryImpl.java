package com.example.foodplanner.data.model;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSourceImpl;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.network.NetworkCallback;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;

import java.util.List;

public class MealsRepositoryImpl implements MealsRepository {
    private static final String TAG = "TAG";

    ProductRemoteDataSourceImpl productRemoteDataSource;
    FaviourtLocalDataSourceImpl prodcutsLocalDataSource;
    PlannedLocalDataSourceImpl plannedLocalDataSource;
    private static MealsRepositoryImpl repo = null;

    public static MealsRepositoryImpl getInstance(ProductRemoteDataSourceImpl productRemoteDataSource,
                                                  FaviourtLocalDataSourceImpl prodcutsLocalDataSource,
                                                  PlannedLocalDataSourceImpl plannedLocalDataSource) {
        if (repo == null) {
            repo = new MealsRepositoryImpl(productRemoteDataSource, prodcutsLocalDataSource, plannedLocalDataSource);
        }
        return repo;
    }

    private MealsRepositoryImpl(ProductRemoteDataSourceImpl productRemoteDataSource,
                                FaviourtLocalDataSourceImpl prodcutsLocalDataSource,
                                PlannedLocalDataSourceImpl plannedLocalDataSource) {
        this.prodcutsLocalDataSource = prodcutsLocalDataSource;
        this.productRemoteDataSource = productRemoteDataSource;
        this.plannedLocalDataSource = plannedLocalDataSource;
    }
// it impact to live
    public LiveData<List<FavMeals>> getStoredProducts() {
        return prodcutsLocalDataSource.getStoredFvProduct();
    }

    public LiveData<List<PlannedMeals>> getStoredplannedProducts() {
        return plannedLocalDataSource.getStoredPlannedMeals();
    }

    @Override
    public void getAllInsperMeals(NetworkCallback networkCallback) {
        productRemoteDataSource.makeNetworkCall(networkCallback);
    }

    @Override
    public void insertinFavTable(FavMeals favMeals) {
        prodcutsLocalDataSource.insert(favMeals);
    }

    @Override
    public void deleteFromFav(FavMeals favMeals) {

        prodcutsLocalDataSource.delete(favMeals);
    }

    @Override
    public void insertintoPlanTable(PlannedMeals plannedMeal) {
        Log.i(TAG, "plannedMeal" );
        plannedLocalDataSource.insert(plannedMeal);
    }

    @Override
    public void deleteFromPlanTable(PlannedMeals plannedMeal) {
        plannedLocalDataSource.delete(plannedMeal);
    }
}

