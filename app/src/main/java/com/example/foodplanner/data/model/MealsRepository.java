package com.example.foodplanner.data.model;

import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface MealsRepository {

    Flowable<List<FavMeals>> getStoredFvProduct();


    Observable<MealsResponse> getAllProducts();
    Observable<CategoryResponse> getAllCategoreis();

    Observable<MealsResponse> getCatgoryMeals(String categoryName);

    Observable<MealsResponse> getAreaMeals(String areaName);

    Observable<MealsResponse> getMealDetails(String mealName);

//    Observable<IngredientResponse> getIngredient();

    Observable<MealsResponse> getMealsByIngredient(String ingName);

    Observable<AreaResponse> getAreas();


    //>>>>>>>>>>>>>>>>>>>>>>...for fav<<<<<<<<<
    Completable insertinFavTable(FavMeals favmeal, MealCard mealCard);

    Completable deleteFromFav(FavMeals favMeal, MealCard mealCard);


    //>>>>>>>>>>>>>>>>>>>>>>...for plan<<<<<<<<<
    void insertintoPlanTable(List<PlannedMeals> meals, PlannedMeals plannedMeal , String day);

    void deleteFromPlanTable(PlannedMeals plannedMeal, String day);
}

