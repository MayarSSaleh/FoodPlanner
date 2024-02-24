package com.example.foodplanner.screens.Card.view;

import com.example.foodplanner.data.model.Meal;

public interface MealCardView {
   public   void setThisMealAtCard(Meal mealCard);

    public  void notGetTheMealDetails(String error);
}
