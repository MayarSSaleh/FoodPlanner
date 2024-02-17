package com.example.foodplanner.data.model;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.data.firebase.UpdateFirebase;
import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

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

    @Override
    public Flowable<List<FavMeals>> getStoredFvProduct() {
        return prodcutsLocalDataSource.getStoredFvProduct();
    }

    @Override
    public Completable insertinFavTable(FavMeals favmeal, MealCard mealCard) {
        UpdateFirebase.addMealToFirebase(mealCard);

        return prodcutsLocalDataSource.insert(favmeal);
    }

    @Override
    public Completable deleteFromFav(FavMeals favMeal, MealCard mealCard) {
        UpdateFirebase.removeMealFromFirebase(mealCard);

        return prodcutsLocalDataSource.delete(favMeal);
    }

    //plllllllllllllllllllllllllllan
    public LiveData<List<PlannedMeals>> getStoredplannedProducts() {
        return plannedLocalDataSource.getStoredPlannedMeals();
    }

    // random meal
    @Override
    public Observable<MealsResponse> getAllProducts() {
        return productRemoteDataSource.makeNetworkCall();
    }


    @Override
    public Observable<CategoryResponse> getAllCategoreis() {
        return productRemoteDataSource.getCategories();
    }

    @Override
    public Observable<MealsResponse> getCatgoryMeals(String categoryName) {
        return productRemoteDataSource.getMealcategories(categoryName);
    }

    @Override
    public Observable<MealsResponse> getAreaMeals(String areaName) {
        return productRemoteDataSource.getAreaMeals(areaName);
    }

    @Override
    public Observable<MealsResponse> getMealDetails(String mealName) {
        return productRemoteDataSource.getMealDetails(mealName);
    }


//    @Override
//    public Observable<IngredientResponse> getIngredient() {
//        return productRemoteDataSource.getIngredient();
//    }

    @Override
    public Observable<MealsResponse> getMealsByIngredient(String ingName) {
        return productRemoteDataSource.getMealsByIngredient(ingName);
    }


    @Override
    public Observable<AreaResponse> getAreas() {
        return productRemoteDataSource.getAreas();
    }


    @Override
    public void insertintoPlanTable(List<PlannedMeals> meals, PlannedMeals plannedMeal, String day) {
//        Log.i(TAG, "plannedMeal meal repos  imp" + day );
        plannedLocalDataSource.updateOrInsertMeal(meals, plannedMeal, day);
    }

    @Override
    public void deleteFromPlanTable(PlannedMeals plannedMeal, String day) {
        plannedLocalDataSource.updateOrDeletMeal(plannedMeal, day);
    }
}


