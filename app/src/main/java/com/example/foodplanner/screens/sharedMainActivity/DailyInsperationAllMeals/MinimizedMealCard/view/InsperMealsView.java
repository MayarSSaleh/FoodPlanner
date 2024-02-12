package com.example.foodplanner.screens.sharedMainActivity.DailyInsperationAllMeals.MinimizedMealCard.view;

import com.example.foodplanner.data.model.MealCard;

import java.util.List;

public interface InsperMealsView {
    public void showData (MealCard mealCard);
    public void showErrMsg(String error);
}
