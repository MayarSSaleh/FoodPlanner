package com.example.foodplanner.screens.sharedMainActivity.favMeals.favList.presenter;

import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.model.MealCard;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface FavMealsPresenter {


//    void delete(FavMeals favMeal, MealCard meal);

    Flowable<List<FavMeals>> getStoredFvProduct();
}
