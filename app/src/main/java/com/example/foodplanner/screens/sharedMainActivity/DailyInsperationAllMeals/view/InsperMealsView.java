package com.example.foodplanner.screens.sharedMainActivity.DailyInsperationAllMeals.MinimizedMealCard.view;

import com.example.foodplanner.data.model.Meal;

public interface InsperMealsView {
    public void showData (Meal mealCard);
    public void showErrMsg(String error);
}
