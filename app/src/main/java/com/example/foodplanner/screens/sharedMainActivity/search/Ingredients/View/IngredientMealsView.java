package com.example.foodplanner.screens.sharedMainActivity.search.Ingredients.View;

import com.example.foodplanner.data.model.MealCard;

import java.util.ArrayList;

public interface IngredientMealsView {

    public void showData (ArrayList<MealCard> categories);
    public void showErrMsg(String error);


}
