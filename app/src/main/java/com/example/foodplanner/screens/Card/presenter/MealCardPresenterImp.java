package com.example.foodplanner.screens.Card.presenter;

import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.screens.Card.view.MealCardView;
import com.example.foodplanner.screens.sharedMainActivity.favMeals.favList.view.FavMealsView;

import java.util.ArrayList;
import java.util.List;

public class MealCardPresenterImp implements  MealCardPresenter, FavMealsView {
    MealCardView mealCardView;

public void showAtCard(MealCard mealCard){
    for (int i = 1; i <= 20; i++) {
        String ingredientName = "ingr" + i;
        String ingredientAmount = "ingr" + i + "m";
        if (!ingredientName.equals("")) {
            Ingredient ingredient = new Ingredient(ingredientName, ingredientAmount);
            mealCard.getAllingredient().add(ingredient);
        }
    }
}
    @Override
    public void addtoDataBaseFavMeal(MealCard mealCard) {
// here i should change meal card to fav
    }

    @Override
    public void removeFeomDBfavMeal(MealCard mealCard) {
// here i should change meal card to fav
//i only need the primary key to delete
    }

    @Override
    public void showThisFavMeal(FavMeals favMeal) {
    MealCard mealCard = new MealCard();
    mealCard.setName(favMeal.getName());
    mealCard.setCountry(favMeal.getCountry());
    mealCard.setAllingredient(favMeal.getAllingredient());
    mealCard.setSteps(favMeal.getSteps());
    mealCard.setFav(favMeal.isFav());
    mealCard.setPhotourl(favMeal.getPhotourl());
    mealCard.setVideoUrl(favMeal.getVideoUrl());
        mealCardView.setThisMealAtCard(mealCard);
    }
}
