package com.example.foodplanner.screens.Card.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.model.MealsResponse;
import com.example.foodplanner.screens.Card.view.MealCardActivity;
import com.example.foodplanner.screens.Card.view.MealCardView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealCardPresenterImp implements MealCardPresenter {
    MealCardView mealCardView;
    MealsRepositoryImpl repository;
    Context context;

    public MealCardPresenterImp(MealCardView mealCardView, MealsRepositoryImpl repository, Context context) {
        this.mealCardView = mealCardView;
        this.repository = repository;
        this.context = context;
    }

    @Override
    public void getMealDetailsofThisMeal(String mealName) {
        Observable<MealsResponse> observable = repository.getMealDetails(mealName);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealsResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull MealsResponse mealsResponse) {
//                        Log.d("daaaaaaaaa",":::  "  + mealsResponse.meals.size());
//                        Log.d("daaaaaaaaa",":::  "  + mealsResponse.meals.get(0).getName());
                        if ( mealsResponse.meals != null && !mealsResponse.meals.isEmpty()) {
                            mealCardView.setThisMealAtCard(mealsResponse.meals.get(0));
                        }
                        else    mealCardView.notGetTheMealDetails("we try to get your meal ,but not found, try another meal");

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        mealCardView.notGetTheMealDetails(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    @Override
    public ArrayList<Ingredient> getIngredients(MealCard meal) {
        ArrayList<Ingredient> ingredientsList = new ArrayList<>();

        if (meal.getAllingredient().isEmpty()) {
            if (meal.getIngr1() != null && !meal.getIngr1().isEmpty())
                ingredientsList.add(new Ingredient(meal.getIngr1(), meal.getIngr1m()));
            if (meal.getIngr2() != null && !meal.getIngr2().isEmpty())
                ingredientsList.add(new Ingredient(meal.getIngr2(), meal.getIngr2m()));
            if (meal.getIngr3() != null && !meal.getIngr3().isEmpty())
                ingredientsList.add(new Ingredient(meal.getIngr3(), meal.getIngr3m()));
            if (meal.getIngr4() != null && !meal.getIngr4().isEmpty())
                ingredientsList.add(new Ingredient(meal.getIngr4(), meal.getIngr4m()));
            if (meal.getIngr5() != null && meal.getIngr5() != null && !meal.getIngr4().isEmpty())
                ingredientsList.add(new Ingredient(meal.getIngr5(), meal.getIngr5m()));
            if (meal.getIngr6() != null && !meal.getIngr6().isEmpty())
                ingredientsList.add(new Ingredient(meal.getIngr6(), meal.getIngr6m()));
            if (meal.getIngr7() != null && !meal.getIngr7().isEmpty())
                ingredientsList.add(new Ingredient(meal.getIngr7(), meal.getIngr7m()));
            if (meal.getIngr8() != null && !meal.getIngr8().isEmpty())
                ingredientsList.add(new Ingredient(meal.getIngr8(), meal.getIngr8m()));
            if (meal.getIngr9() != null && !meal.getIngr9().isEmpty())
                ingredientsList.add(new Ingredient(meal.getIngr9(), meal.getIngr9m()));
            if (meal.getIngr10() != null && !meal.getIngr10().isEmpty())
                ingredientsList.add(new Ingredient(meal.getIngr10(), meal.getIngr10m()));
            if (meal.getIngr11() != null && !meal.getIngr11().isEmpty())
                ingredientsList.add(new Ingredient(meal.getIngr11(), meal.getIngr11m()));
            if (meal.getIngr12() != null && meal.getIngr12() != null && !meal.getIngr12().isEmpty())
                ingredientsList.add(new Ingredient(meal.getIngr12(), meal.getIngr12m()));
            if (meal.getIngr13() != null && !meal.getIngr13().isEmpty())
                ingredientsList.add(new Ingredient(meal.getIngr13(), meal.getIngr13m()));
            if (meal.getIngr14() != null && !meal.getIngr14().isEmpty())
                ingredientsList.add(new Ingredient(meal.getIngr14(), meal.getIngr14m()));
            if (meal.getIngr15() != null && !meal.getIngr15().isEmpty())
                ingredientsList.add(new Ingredient(meal.getIngr15(), meal.getIngr15m()));
            if (meal.getIngr16() != null && !meal.getIngr16().isEmpty())
                ingredientsList.add(new Ingredient(meal.getIngr16(), meal.getIngr16m()));
            if (meal.getIngr17() != null && !meal.getIngr17().isEmpty())
                ingredientsList.add(new Ingredient(meal.getIngr17(), meal.getIngr17m()));
            if (meal.getIngr18() != null && !meal.getIngr18().isEmpty())
                ingredientsList.add(new Ingredient(meal.getIngr18(), meal.getIngr18m()));
            if (meal.getIngr19() != null && !meal.getIngr19().isEmpty())
                ingredientsList.add(new Ingredient(meal.getIngr19(), meal.getIngr19m()));
            if (meal.getIngr20() != null && !meal.getIngr20().isEmpty()) {
                ingredientsList.add(new Ingredient(meal.getIngr20(), meal.getIngr20m()));
            }
        } else {

            ingredientsList.addAll(meal.getAllingredient());
        }
        return ingredientsList;
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
//        Log.i(TAG, "in meal card presenter imp ");
        repository.insertintoPlanTable(meals, newMeal, day);
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
        newFavMeal.setFav(true);
        newFavMeal.setSteps(mealCard.getSteps());
        repository.insertinFavTable(newFavMeal, mealCard, context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            Toast.makeText(context, "The meal in your Favourites now", Toast.LENGTH_SHORT).show();
                        },
                        error -> {
                            Toast.makeText(context, "Sorry, could not add it. Please try again later", Toast.LENGTH_SHORT).show();
                        }
                );
    }

    @Override
    public void removeFeomDBfavMeal(MealCard mealCard) {
        FavMeals newFavMeal = new FavMeals();
        newFavMeal.setMealId(mealCard.getMealId());
        repository.deleteFromFav(newFavMeal, mealCard, context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            Toast.makeText(context, "Removed from your Favouirt", Toast.LENGTH_SHORT).show();
                        },
                        error -> {
                        }
                );
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

        mealCard.setFav(true);
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
