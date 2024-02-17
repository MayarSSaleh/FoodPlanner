package com.example.foodplanner.screens.sharedMainActivity.search.Area.View;

import com.example.foodplanner.data.model.MealCard;

import java.util.ArrayList;

public interface AreaMealsView {
    void showData(ArrayList<MealCard> categories);
    void showErrMsg(String error);
}
