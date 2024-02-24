package com.example.foodplanner.screens.sharedMainActivity.search.Area.View;

import com.example.foodplanner.data.model.Meal;

import java.util.ArrayList;

public interface AreaMealsView {
    void showData(ArrayList<Meal> categories);
    void showErrMsg(String error);
}
