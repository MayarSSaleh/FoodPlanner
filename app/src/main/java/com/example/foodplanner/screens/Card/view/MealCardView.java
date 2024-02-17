package com.example.foodplanner.screens.Card.view;

import com.example.foodplanner.data.model.MealCard;

public interface MealCardView {
   public   void setThisMealAtCard(MealCard mealCard);

    void notGetTheMealDetails(String error);
}
