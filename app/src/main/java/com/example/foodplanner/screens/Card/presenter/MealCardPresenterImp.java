package com.example.foodplanner.screens.Card.presenter;

import android.util.Log;

import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.screens.Card.view.MealCardView;

import java.util.List;

public class MealCardPresenterImp implements MealCardPresenter {
    MealCardView mealCardView;
    MealsRepositoryImpl repository;

    public MealCardPresenterImp(MealCardView mealCardView, MealsRepositoryImpl repository) {
        this.mealCardView = mealCardView;
        this.repository = repository;
    }

    public void showAtCard(MealCard mealCard) {
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
    public void addToPlan(List<PlannedMeals> meals, MealCard mealCard, String day) {
        PlannedMeals newMeal = new PlannedMeals();
        newMeal.setName(mealCard.getName());
        newMeal.setMealId(mealCard.getMealId());
        newMeal.setCountry(mealCard.getCountry());
        newMeal.setPhotourl(mealCard.getPhotourl());
        newMeal.setVideoUrl(mealCard.getVideoUrl());
        newMeal.setAllingredient(mealCard.getAllingredient());
        newMeal.setFav(mealCard.isFav());
        newMeal.setSteps(mealCard.getSteps());
        Log.i(TAG, "mealCard day " + day);

        if (day.equals("Saturday")) {
            newMeal.setSaturday(true);
        } else if (day.equals("Sunday")) {
            newMeal.setSunday(true);
        } else if (day.equals("Monday")) {
            newMeal.setMonday(true);
        } else if (day.equals("Tuesday")) {
            newMeal.setTuesday(true);
        } else if (day.equals("Wednesday")) {
            newMeal.setWednesday(true);
        } else if (day.equals("Thursday")) {
            newMeal.setThursday(true);
        } else if (day.equals("Friday")) {
            newMeal.setFriday(true);
        }
        Log.i(TAG, "in meal card presenter imp ");
        repository.insertintoPlanTable(meals,newMeal,day);
    }

    @Override
    public void addtoDataBaseFavMeal(MealCard mealCard) {
        FavMeals newFavMeal = new FavMeals();
        newFavMeal.setName(mealCard.getName());
        newFavMeal.setMealId(mealCard.getMealId());
        newFavMeal.setCountry(mealCard.getCountry());
        newFavMeal.setPhotourl(mealCard.getPhotourl());
        newFavMeal.setVideoUrl(mealCard.getVideoUrl());
        newFavMeal.setAllingredient(mealCard.getAllingredient());
        newFavMeal.setFav(mealCard.isFav());
        newFavMeal.setSteps(mealCard.getSteps());
        repository.insertinFavTable(newFavMeal);
    }

    @Override
    public void removeFeomDBfavMeal(MealCard mealCard) {
        FavMeals newFavMeal = new FavMeals();
        newFavMeal.setMealId(mealCard.getMealId());
        repository.deleteFromFav(newFavMeal);
        // here i should change meal card to fav
//i only need the primary key to delete
    }

    String TAG = "TAG";

    public void showThisFavMeal(FavMeals favMeal) {

        MealCard mealCard = new MealCard();
        mealCard.setMealId(favMeal.getMealId());
        mealCard.setName(favMeal.getName());
        mealCard.setCountry(favMeal.getCountry());
        mealCard.setAllingredient(favMeal.getAllingredient());
//        Log.i(TAG, "showThisFavMeal:fav .getsteps " + favMeal.getSteps());
        mealCard.setSteps(favMeal.getSteps());
//        Log.i(TAG, "mealCard " + mealCard.getSteps());

        mealCard.setFav(favMeal.isFav());
        mealCard.setPhotourl(favMeal.getPhotourl());
        mealCard.setVideoUrl(favMeal.getVideoUrl());
        mealCardView.setThisMealAtCard(mealCard);
    }

    public void showThisPlannedMeal(PlannedMeals plannedMeal) {
        MealCard mealCard = new MealCard();
        mealCard.setMealId(plannedMeal.getMealId());
        mealCard.setName(plannedMeal.getName());
        mealCard.setCountry(plannedMeal.getCountry());
        mealCard.setAllingredient(plannedMeal.getAllingredient());
        Log.i(TAG, "showThisFavMeal:fav .getsteps " + plannedMeal.getSteps());
        mealCard.setSteps(plannedMeal.getSteps());
        Log.i(TAG, "mealCard " + mealCard.getSteps());
        mealCard.setFav(plannedMeal.isFav());
        mealCard.setPhotourl(plannedMeal.getPhotourl());
        mealCard.setVideoUrl(plannedMeal.getVideoUrl());
        mealCardView.setThisMealAtCard(mealCard);

    }
}
