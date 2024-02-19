package com.example.foodplanner.data.model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

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
    public Completable insertinFavTable(FavMeals favmeal, MealCard mealCard, Context c) {
        UpdateFirebase.addMealToFirebase(mealCard, c);
        return prodcutsLocalDataSource.insert(favmeal);
    }

    @Override
    public void addPlannedMealToFirebaseRepo(LifecycleOwner lifecycleOwner, Context context) {
        LiveData<List<PlannedMeals>> savedMeals = plannedLocalDataSource.getStoredPlannedMeals();
        savedMeals.observe(lifecycleOwner, new Observer<List<PlannedMeals>>() {
            @Override
            public void onChanged(List<PlannedMeals> lifeSavedMeals) {
                if (savedMeals != null) {
                    for (PlannedMeals meal : lifeSavedMeals) {
//                        Log.d("keep", "in repo" + meal.getName());
                        UpdateFirebase.addPlannedMealToFirebase(meal, context);
                    }
                }
            }
        });
    }

    public void removeAllPlannedMeals() {
        plannedLocalDataSource.deleteAll();
    }

    public void insertFromFirebaseToLocalPlanTable(PlannedMeals meal) {
        plannedLocalDataSource.insertToPlan(meal);
    }

    @Override
    public Completable insertFromFirbaseToLocalFavTable(FavMeals favmeal) {
        return prodcutsLocalDataSource.insert(favmeal);
    }

    @Override
    public Completable deleteFromFav(FavMeals favMeal, MealCard mealCard, Context context) {
        UpdateFirebase.removeMealFromFirebase(mealCard, context);
        return prodcutsLocalDataSource.delete(favMeal);
    }


    @Override
    public Completable deleteAllFav() {
        return prodcutsLocalDataSource.deleteAll();
    }


    public LiveData<List<PlannedMeals>> getStoredplannedProducts() {
        return plannedLocalDataSource.getStoredPlannedMeals();
    }

    // random meal
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

    public void getUserData(Context context) {
//        Toast.makeText(context, "get user data to gire base", Toast.LENGTH_SHORT).show();

        UpdateFirebase.getAllUserDataFromFirebase(context);
    }
}


