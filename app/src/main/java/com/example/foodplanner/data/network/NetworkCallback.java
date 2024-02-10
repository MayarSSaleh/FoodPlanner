package com.example.foodplanner.data.network;

import com.example.foodplanner.data.model.MealCard;

import java.util.List;

public interface NetworkCallback {
    public void onSuccessResultForRandom(List<MealCard> mealCardList);
//    public void onSuccessResultSearch();
//    public void onSuccessResultForRandom();
//    public void onSuccessResultForRandom();
//    public void onSuccessResultForRandom(List<MealCard> products);

    public void onFailureResult(String errorMsg);
}
