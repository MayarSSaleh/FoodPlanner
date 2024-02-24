package com.example.foodplanner.screens.Card.view;

import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.model.Meal;
import java.util.List;

public interface OnAddToPlanClickListener {
    void addToPlan(List<PlannedMeals> meals, Meal mealCard, String day);
}