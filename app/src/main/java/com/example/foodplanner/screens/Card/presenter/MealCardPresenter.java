package com.example.foodplanner.screens.Card.presenter;

import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.model.MealCard;

public interface MealCardPresenter {
    public void addToPlan(MealCard meal,String day);
    public void addtoDataBaseFavMeal(MealCard mealCard);
    public void removeFeomDBfavMeal(MealCard mealCard);

}
