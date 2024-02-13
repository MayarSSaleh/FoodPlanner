package com.example.foodplanner.screens.Card.presenter;

import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.model.MealCard;

import java.util.List;

public interface MealCardPresenter {
    public void addToPlan(List<PlannedMeals> meals, MealCard meal, String day);
    public void addtoDataBaseFavMeal(MealCard mealCard);
    public void removeFeomDBfavMeal(MealCard mealCard);

}
