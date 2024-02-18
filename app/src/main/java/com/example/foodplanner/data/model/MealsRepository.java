package com.example.foodplanner.data.model;

import android.content.Context;
import android.util.Log;
import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSource;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface MealsRepository {
    String TAG = "TAG";

    static void insertAllFavMeals(MealCard meal, Context context) {
        Log.d("keep", "inside is login so get the data fun " + meal.getName());

        FaviourtLocalDataSource productsLocalDataSource = new FaviourtLocalDataSource(context);
        FavMeals newFavMeal = new FavMeals();
        newFavMeal.setName(meal.getName());
        newFavMeal.setMealId(meal.getMealId());
        newFavMeal.setCountry(meal.getCountry());
        newFavMeal.setPhotourl(meal.getPhotourl());
        newFavMeal.setVideoUrl(meal.getVideoUrl());
        newFavMeal.setAllingredient(meal.getAllingredient());
        newFavMeal.setFav(true);
        newFavMeal.setSteps(meal.getSteps());
        productsLocalDataSource.insert(newFavMeal);
    }

    Flowable<List<FavMeals>> getStoredFvProduct();

    Completable deleteAllFav();

    Observable<MealsResponse> getRandomMeal();

    Observable<CategoryResponse> getAllCategories();

    Observable<MealsResponse> getCategoryMeals(String categoryName);

    Observable<MealsResponse> getAreaMeals(String areaName);

    Observable<MealsResponse> getMealDetails(String mealName);

    Observable<MealsResponse> getMealsByIngredient(String ingName);

    Observable<AreaResponse> getAreas();
//    Observable<IngredientResponse> getIngredient();

    //>>>>>>>>>>>>>>>>>>>>>>...for fav<<<<<<<<<

    Completable insertinFavTable(FavMeals favmeal, MealCard mealCard, Context c);

    Completable deleteFromFav(FavMeals favMeal, MealCard mealCard, Context context);


    //>>>>>>>>>>>>>>>>>>>>>>...for plan<<<<<<<<<
    void insertintoPlanTable(List<PlannedMeals> meals, PlannedMeals plannedMeal, String day);

    void deleteFromPlanTable(PlannedMeals plannedMeal, String day);

}

