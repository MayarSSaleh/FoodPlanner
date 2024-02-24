package com.example.foodplanner.data.model;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface MealsRepository {
    String TAG = "TAG";


    Completable insertInFavTable(Meal mealCard, Context c);

    void addPlannedMealToFirebaseRepoThenRemoveLocal(LifecycleOwner lifecycleOwner, Context context);


    void deletePlannedMeals();

    void insertFromFirebaseToLocalPlanTable(PlannedMeals meal);

     Flowable<List<Meal>> getStoredFvProduct();

    Completable insertFromFirbaseToLocalFavTable(Meal favmeal);

    Completable deleteFromFav(Meal mealCard, Context context);

    Completable deleteAllFav();

    Observable<MealsResponse> getRandomMeal();

    Observable<CategoryResponse> getAllCategories();

    Observable<MealsResponse> getCategoryMeals(String categoryName);

    Observable<MealsResponse> getAreaMeals(String areaName);

    Observable<MealsResponse> getMealDetails(String mealName);

    Observable<MealsResponse> getMealsByIngredient(String ingName);

    Observable<AreaResponse> getAreas();
//    Observable<IngredientResponse> getIngredient();
    

    //>>>>>>>>>>>>>>>>>>>>>>...for plan<<<<<<<<<

    void insertInToPlanTable(List<PlannedMeals> meals, PlannedMeals plannedMeal, String day, Context context);

    void deleteFromPlanTable(PlannedMeals plannedMeal, String day, Context context);

    void getUserData(Context context);
}

