package com.example.foodplanner.screens.sharedMainActivity.search.Categry.View;

import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.data.model.MealsResponse;
import java.util.ArrayList;

public interface CategoryMealsView {

    public void showData (ArrayList<MealCard> categories);
    public void showErrMsg(String error);


}
