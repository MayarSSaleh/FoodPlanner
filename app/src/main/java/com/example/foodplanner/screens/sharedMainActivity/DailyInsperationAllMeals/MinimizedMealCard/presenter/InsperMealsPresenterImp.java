package com.example.foodplanner.screens.sharedMainActivity.DailyInsperationAllMeals.MinimizedMealCard.presenter;

import android.util.Log;

import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.data.model.MealsRepository;
import com.example.foodplanner.data.network.NetworkCallback;
import com.example.foodplanner.screens.sharedMainActivity.DailyInsperationAllMeals.MinimizedMealCard.view.InsperMealsView;
import java.util.List;

public class InsperMealsPresenterImp implements InsperMealsPresenter, NetworkCallback {
    private static final String TAG="TAG";
    MealsRepository mealsRepository;
    InsperMealsView allMealsView;

    public InsperMealsPresenterImp(MealsRepository mealsRepository, InsperMealsView allMealsView) {
        this.mealsRepository = mealsRepository;
        this.allMealsView = allMealsView;
    }
    @Override
    public void getAllProducts() {
        mealsRepository.getAllInsperMeals(this);
    }
    @Override
    public void onSuccessResultForRandom(List<MealCard> meals) {
        allMealsView.showData(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        Log.i(TAG, "onFailureResult: allMealsPresenter");
    }

}
