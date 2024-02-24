package com.example.foodplanner.screens.Card.view;

import com.example.foodplanner.data.model.Meal;

public interface MealCardView {


    void setThisMealAtCard(Meal mealCard);

    void notGetTheMealDetails(String error);
}
