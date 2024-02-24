package com.example.foodplanner.screens.sharedMainActivity.search.Categry.View;

import com.example.foodplanner.data.model.Meal;

import java.util.ArrayList;

public interface CategoryMealsView {

    public void showData (ArrayList<Meal> categories);
    public void showErrMsg(String error);


}
