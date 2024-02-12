package com.example.foodplanner.screens.sharedMainActivity.DailyInsperationAllMeals.MinimizedMealCard.presenter;

import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.data.model.MealsRepository;
import com.example.foodplanner.data.network.NetworkCallback;
import com.example.foodplanner.screens.sharedMainActivity.DailyInsperationAllMeals.MinimizedMealCard.view.DailyInspFragment;
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
    public void onSuccessResultForRandom(List<MealCard> mealCards) {
        allMealsView.showData(mealCards.get(0));
 }

    @Override
    public void onFailureResult(String errorMsg) {

//        Toast.makeText(InsperMealsPresenterImp., "Sorry, Error in Loading, Please check your internet connection", Toast.LENGTH_LONG).show();
    }
}
