package com.example.foodplanner.screens.sharedMainActivity.search.Ingredients.View;

import com.example.foodplanner.data.model.Meal;

import java.util.ArrayList;

public interface IngredientMealsView {

    public void showData (ArrayList<Meal> categories);
    public void showErrMsg(String error);


}
