package com.example.foodplanner.screens.sharedMainActivity.favMeals.favList.presenter;

import com.example.foodplanner.data.local_db.favMeals.FavMeals;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface FavMealsPresenter {

    void delete(FavMeals favMeal);

    Flowable<List<FavMeals>> getStoredFvProduct();
}
