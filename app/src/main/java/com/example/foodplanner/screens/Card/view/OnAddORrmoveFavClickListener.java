package com.example.foodplanner.screens.Card.view;

import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.model.MealCard;

public interface OnAddORrmoveFavClickListener {

    public void addToFav(MealCard mealCard);

    public void removeFromFav(MealCard mealCard);
}
