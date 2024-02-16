package com.example.foodplanner.data.model;

import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public interface MealsRepository {

    Observable<MealsResponse> getAllProducts();
    Observable<CategoryResponse> getAllCategoreis();

    Observable<MealsResponse> getCatgoryMeals(String categoryName);

    Observable<MealsResponse> getMealDetails(String mealName);

    Observable<AreaResponse> getAreas();


    //>>>>>>>>>>>>>>>>>>>>>>...for fav<<<<<<<<<

    public Completable insertinFavTable(FavMeals mealCard);
    public  Completable deleteFromFav(FavMeals favMeals);


    //>>>>>>>>>>>>>>>>>>>>>>...for plan<<<<<<<<<
    void insertintoPlanTable(List<PlannedMeals> meals, PlannedMeals plannedMeal , String day);

    void deleteFromPlanTable(PlannedMeals plannedMeal, String day);
}

