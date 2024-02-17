package com.example.foodplanner.data.network;

import com.example.foodplanner.data.model.AreaResponse;
import com.example.foodplanner.data.model.CategoryResponse;
import com.example.foodplanner.data.model.IngredientResponse;
import com.example.foodplanner.data.model.MealsResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealsService {


    @GET("random.php")
    Observable<MealsResponse> getMeal();

    @GET("categories.php")
    Observable<CategoryResponse> getCategories();
    @GET("filter.php?")
    Observable<MealsResponse> getCategoryMeals(@Query("c") String categoryName);

    @GET("search.php?s=")
    Observable<MealsResponse> getMealDetails(@Query("s") String mealName);



    @GET("list.php?a=list")
    Observable<AreaResponse> getAreas();
    @GET("filter.php?a=")
    Observable<MealsResponse> getAreaMeals(@Query("a") String areaName);


    @GET("list.php?i=list")
    Observable<IngredientResponse> getIngredient();
    @GET("filter.php")
     Observable<MealsResponse>getMealsByIngredient(@Query("i") String i);

}
