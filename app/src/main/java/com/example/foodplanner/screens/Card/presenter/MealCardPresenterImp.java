package com.example.foodplanner.screens.Card.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.model.MealsResponse;
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
    String TAG = "TAG";

    public MealCardPresenterImp(MealCardView mealCardView, MealsRepositoryImpl repository, Context context) {
        this.mealCardView = mealCardView;
        this.repository = repository;
        this.context = context;
    }

    @Override
    public void getMealDetailsOfThisMeal(String mealName) {
        Observable<MealsResponse> observable = repository.getMealDetails(mealName);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealsResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull MealsResponse mealsResponse) {
                       if (mealsResponse.meals != null && !mealsResponse.meals.isEmpty()) {
                            mealCardView.setThisMealAtCard(mealsResponse.meals.get(0));
                        } else
                            mealCardView.notGetTheMealDetails("we try to get your meal ,but not found, try another meal");
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        mealCardView.notGetTheMealDetails(e.getMessage());
                    }

                    @Override
                    public void onComplete() {}
                });
    }


    @Override
    public ArrayList<Ingredient> getIngredients(Meal meal) {
        ArrayList<Ingredient> ingredientsList = new ArrayList<>();

        if (meal.getAllIngredient().isEmpty()) {
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
            ingredientsList.addAll(meal.getAllIngredient());
        }
        return ingredientsList;
    }

    @Override
    public void addToPlan(List<PlannedMeals> meals, Meal mealCard, String day) {
        PlannedMeals newMeal = new PlannedMeals();
        switch (day) {
            case "Saturday":
                newMeal.setSaturday(true);
                break;
            case "Sunday":
                newMeal.setSunday(true);
                break;
            case "Monday":
                newMeal.setMonday(true);
                break;
            case "Tuesday":
                newMeal.setTuesday(true);
                break;
            case "Wednesday":
                newMeal.setWednesday(true);
                break;
            case "Thursday":
                newMeal.setThursday(true);
                break;
            case "Friday":
                newMeal.setFriday(true);
                break;
        }
        newMeal.setMealId(mealCard.getMealId());
        newMeal.setPlannedMeal(mealCard);
        repository.insertInToPlanTable(meals, newMeal, day, context);
    }

    @Override
    public void addToDataBaseFavMeal(Meal mealCard) {
        repository.insertInFavTable(mealCard, context)
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
    public void removeFromDBFavMeal(Meal mealCard) {
        repository.deleteFromFav(mealCard, context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            Toast.makeText(context, "Removed from your Favouirte", Toast.LENGTH_SHORT).show();
                        },
                        error -> {
                        }
                );
    }

}
