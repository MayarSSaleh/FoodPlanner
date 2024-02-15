package com.example.foodplanner.data.model;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.network.NetworkCallback;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class MealsRepositoryImpl implements MealsRepository {
    private static final String TAG = "TAG";

    ProductRemoteDataSourceImpl productRemoteDataSource;
    FaviourtLocalDataSource prodcutsLocalDataSource;
    PlannedLocalDataSourceImpl plannedLocalDataSource;
    private static MealsRepositoryImpl repo = null;

    public static MealsRepositoryImpl getInstance(ProductRemoteDataSourceImpl productRemoteDataSource,
                                                  FaviourtLocalDataSource prodcutsLocalDataSource,
                                                  PlannedLocalDataSourceImpl plannedLocalDataSource) {
        if (repo == null) {
            repo = new MealsRepositoryImpl(productRemoteDataSource, prodcutsLocalDataSource, plannedLocalDataSource);
        }
        return repo;
    }

    private MealsRepositoryImpl(ProductRemoteDataSourceImpl productRemoteDataSource,
                                FaviourtLocalDataSource prodcutsLocalDataSource,
                                PlannedLocalDataSourceImpl plannedLocalDataSource) {
        this.prodcutsLocalDataSource = prodcutsLocalDataSource;
        this.productRemoteDataSource = productRemoteDataSource;
        this.plannedLocalDataSource = plannedLocalDataSource;
    }
// it impact to live
//    public LiveData<List<FavMeals>> getStoredProducts() {
//        return prodcutsLocalDataSource.getStoredFvProduct();
//    }
    public Flowable<List<FavMeals>> getStoredFvProduct() {
        return prodcutsLocalDataSource.getStoredFvProduct();
    }

    @Override
    public Completable insertinFavTable(FavMeals mealCard) {
        return prodcutsLocalDataSource.insert(mealCard);
    }
    @Override
//    public void deleteFromFav(FavMeals favMeals) {
//        prodcutsLocalDataSource.delete(favMeals);
//    }
    public Completable deleteFromFav(FavMeals mealCard) {
        return prodcutsLocalDataSource.delete(mealCard);
    }

    //plllllllllllllllllllllllllllan
    public LiveData<List<PlannedMeals>> getStoredplannedProducts() {
        return plannedLocalDataSource.getStoredPlannedMeals();
    }

    @Override
    public void getAllInsperMeals(NetworkCallback networkCallback) {
        productRemoteDataSource.makeNetworkCall(networkCallback);
    }

    @Override
    public void insertintoPlanTable(List<PlannedMeals> meals, PlannedMeals plannedMeal , String day) {
        Log.i(TAG, "plannedMeal meal repos  imp" + day );

        plannedLocalDataSource.updateOrInsertMeal(meals,plannedMeal ,day);
    }

    @Override
    public void deleteFromPlanTable(PlannedMeals plannedMeal, String day) {
        plannedLocalDataSource.updateOrDeletMeal(plannedMeal, day);
    }
}


