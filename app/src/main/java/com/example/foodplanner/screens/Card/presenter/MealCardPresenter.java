package com.example.foodplanner.screens.Card.presenter;

import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.Meal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface MealCardPresenter {
    void getMealDetailsOfThisMeal(String meal);
    ArrayList<Ingredient> getIngredients(Meal meal);
    public void addToPlan(List<PlannedMeals> meals, Meal meal, String day);
    public void addToDataBaseFavMeal(Meal mealCard);
    public void removeFromDBFavMeal(Meal mealCard);

    Flowable<Boolean> isInFavMeals(Meal checkThisMeal);
}
