package com.example.foodplanner.screens.Card.view;

import com.example.foodplanner.data.model.Meal;

public interface OnAddORemoveFavClickListener {

    public void addToFav(Meal mealCard);

    public void removeFromFav(Meal mealCard);
}
