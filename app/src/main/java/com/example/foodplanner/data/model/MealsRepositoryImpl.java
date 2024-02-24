package com.example.foodplanner.data.model;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

//import com.example.foodplanner.data.firebase.UpdateFirebase;
import com.example.foodplanner.data.firebase.Firebase;
import com.example.foodplanner.data.local_db.favMeals.FavouriteLocalDataSource;
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
    FavouriteLocalDataSource favouriteLocalDataSource;
    PlannedLocalDataSourceImpl plannedLocalDataSource;
    private static MealsRepositoryImpl repo = null;

    public static MealsRepositoryImpl getInstance(ProductRemoteDataSourceImpl productRemoteDataSource,
                                                  FavouriteLocalDataSource favouriteLocalDataSource,
                                                  PlannedLocalDataSourceImpl plannedLocalDataSource) {
        if (repo == null) {
            repo = new MealsRepositoryImpl(productRemoteDataSource, favouriteLocalDataSource, plannedLocalDataSource);
        }
        return repo;
    }

    private MealsRepositoryImpl(ProductRemoteDataSourceImpl productRemoteDataSource,
                                FavouriteLocalDataSource favouriteLocalDataSource,
                                PlannedLocalDataSourceImpl plannedLocalDataSource) {
        this.favouriteLocalDataSource = favouriteLocalDataSource;
        this.productRemoteDataSource = productRemoteDataSource;
        this.plannedLocalDataSource = plannedLocalDataSource;
    }

    @Override
    public Flowable<List<Meal>> getStoredFvProduct() {
        return favouriteLocalDataSource.getStoredFvProduct();
    }

    @Override
    public Completable insertInFavTable(Meal mealCard, Context c) {
        Firebase.addFavMealToFirebase(mealCard, c);
        return favouriteLocalDataSource.insert(mealCard);
    }

    @Override
    public void deletePlannedMeals() {
        plannedLocalDataSource.deleteAll();
    }

    @Override
    public void insertFromFirebaseToLocalPlanTable(PlannedMeals meal) {
        plannedLocalDataSource.insertToPlan(meal);
    }

    @Override
    public Completable insertFromFirbaseToLocalFavTable(Meal favmeal) {
        return favouriteLocalDataSource.insert(favmeal);
    }

    @Override
    public Completable deleteFromFav(Meal mealCard, Context context) {
        Firebase.removeFavMealFromFirebase(mealCard, context);
        return favouriteLocalDataSource.delete(mealCard);
    }


    @Override
    public Completable deleteAllFav() {
        return favouriteLocalDataSource.deleteAll();
    }
    @Override
    public LiveData<List<PlannedMeals>> getStoredplannedProducts() {
        return plannedLocalDataSource.getStoredPlannedMeals();
    }

    @Override
    public Observable<MealsResponse> getRandomMeal() {
        return productRemoteDataSource.getRandomMeal();
    }


    @Override
    public Observable<CategoryResponse> getAllCategories() {
        return productRemoteDataSource.getCategories();
    }

    @Override
    public Observable<MealsResponse> getCategoryMeals(String categoryName) {
        return productRemoteDataSource.getCategoryMeals(categoryName);
    }

    @Override
    public Observable<MealsResponse> getAreaMeals(String areaName) {
        return productRemoteDataSource.getAreaMeals(areaName);
    }

    @Override
    public Observable<MealsResponse> getMealDetails(String mealName) {
        return productRemoteDataSource.getMealDetails(mealName);
    }

    @Override
    public Observable<MealsResponse> getMealsByIngredient(String ingName) {
        return productRemoteDataSource.getMealsByIngredient(ingName);
    }

    @Override
    public Observable<AreaResponse> getAreas() {
        return productRemoteDataSource.getAreas();
    }

    @Override
    public void insertInToPlanTable(List<PlannedMeals> meals, PlannedMeals plannedMeal, String day, Context context) {
//       instead of make the add here then separated local plan and firebase check if the meal is already store, i will put the firebase in the local plan
//        Firebase.addPlannedMealToFirebase(plannedMeal, context);
        plannedLocalDataSource.updateOrInsertMeal(meals, plannedMeal, day);
    }

    @Override
    public void deleteFromPlanTable(PlannedMeals plannedMeal, String day, Context context) {
//       instead of make the remove here then separated local plan and firebase check if the meal is already store, i will put the firebase in the local plan
//        Firebase.removePlannedMealFromFirebase(plannedMeal, context);
        plannedLocalDataSource.updateOrDeleteMeal(plannedMeal, day);
    }

    @Override
    public void getUserData(Context context) {
        Firebase.getAllUserDataFromFirebase(context);
    }
}


