package com.example.foodplanner.data.local_db.plannedMeals;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.data.firebase.Firebase;
import com.example.foodplanner.data.local_db.APPDataBase;
import com.example.foodplanner.data.local_db.favMeals.FavouriteLocalDataSource;

import java.util.List;

public class PlannedLocalDataSourceImpl {
    private Context context;
    private PlannedMealsDAO mealDAO;
    private LiveData<List<PlannedMeals>> storedPlannedMeals;
    private static FavouriteLocalDataSource rep = null;

    public PlannedLocalDataSourceImpl(Context context) {
        this.context = context;
        APPDataBase db = APPDataBase.getInstance(context);
        mealDAO = db.getPlannedMealsDAO();
        storedPlannedMeals = mealDAO.getAllPlannedMeals();
    }

    public static FavouriteLocalDataSource getInstance(Context context) {
        if (rep == null) {
            rep = new FavouriteLocalDataSource(context);
        }
        return rep;
    }

    public LiveData<List<PlannedMeals>> getStoredPlannedMeals() {
        return storedPlannedMeals;
    }

    public void updateOrInsertMeal(List<PlannedMeals> meals, PlannedMeals storeMealAtPlan, String day) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (meals != null && !meals.isEmpty()) {
                    PlannedMeals savedMeal =
                            meals.stream().filter(meal -> storeMealAtPlan.getMealId().equals(meal.getMealId())).findAny().orElse(null);
                    if (savedMeal != null) {
                        switch (day) {
                            case "Saturday":
                                mealDAO.updateSaturday(storeMealAtPlan.getMealId(), true);
                                Firebase.updatePlannedMealFridayInFirebase(storeMealAtPlan.getMealId(), "Saturday", true, context);
                                break;
                            case "Sunday":
                                mealDAO.updateSunday(storeMealAtPlan.getMealId(), true);
                                Firebase.updatePlannedMealFridayInFirebase(storeMealAtPlan.getMealId(), "Sunday", true, context);
                                break;
                            case "Monday":
                                mealDAO.updateMonday(storeMealAtPlan.getMealId(), true);
                                Firebase.updatePlannedMealFridayInFirebase(storeMealAtPlan.getMealId(), "Monday", true, context);
                                break;
                            case "Tuesday":
                                mealDAO.updateTuesday(storeMealAtPlan.getMealId(), true);
                                Firebase.updatePlannedMealFridayInFirebase(storeMealAtPlan.getMealId(), "Tuesday", true, context);
                                break;
                            case "Wednesday":
                                mealDAO.updateWednesday(storeMealAtPlan.getMealId(), true);
                                Firebase.updatePlannedMealFridayInFirebase(storeMealAtPlan.getMealId(), "Wednesday", true, context);
                                break;
                            case "Thursday":
                                mealDAO.updateThursday(storeMealAtPlan.getMealId(), true);
                                Firebase.updatePlannedMealFridayInFirebase(storeMealAtPlan.getMealId(), "Thursday", true, context);
                                break;
                            case "Friday":
                                mealDAO.updateFriday(storeMealAtPlan.getMealId(), true);
                                Firebase.updatePlannedMealFridayInFirebase(storeMealAtPlan.getMealId(), "Friday", true, context);
                                break;
                        }
                    } else {
                        mealDAO.insert(storeMealAtPlan);
                        Firebase.addPlannedMealToFirebase(storeMealAtPlan, context);
                    }
                } else {
                    mealDAO.insert(storeMealAtPlan);
                    Firebase.addPlannedMealToFirebase(storeMealAtPlan, context);

                }
            }
        }).start();
    }

    public void updateOrDeleteMeal(PlannedMeals removeMealFromDay, String day) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                switch (day) {
                    case "Saturday":
                        removeMealFromDay.setSaturday(false);
                        mealDAO.updateSaturday(removeMealFromDay.getMealId(), false);
                        Firebase.updatePlannedMealFridayInFirebase(removeMealFromDay.getMealId(), "Saturday", false, context);
                        mealStoredAtAnyDay(removeMealFromDay);
                        break;
                    case "Sunday":
                        mealDAO.updateSunday(removeMealFromDay.getMealId(), false);
                        removeMealFromDay.setSunday(false);
                        Firebase.updatePlannedMealFridayInFirebase(removeMealFromDay.getMealId(), "Sunday", false, context);
                        mealStoredAtAnyDay(removeMealFromDay);
                        break;
                    case "Monday":
                        mealDAO.updateMonday(removeMealFromDay.getMealId(), false);
                        removeMealFromDay.setMonday(false);
                        Firebase.updatePlannedMealFridayInFirebase(removeMealFromDay.getMealId(), "Monday", false, context);
                        mealStoredAtAnyDay(removeMealFromDay);
                        break;
                    case "Tuesday":
                        mealDAO.updateTuesday(removeMealFromDay.getMealId(), false);
                        removeMealFromDay.setTuesday(false);
                        Firebase.updatePlannedMealFridayInFirebase(removeMealFromDay.getMealId(), "Tuesday", false, context);
                        mealStoredAtAnyDay(removeMealFromDay);
                        break;
                    case "Wednesday":
                        mealDAO.updateWednesday(removeMealFromDay.getMealId(), false);
                        removeMealFromDay.setWednesday(false);
                        Firebase.updatePlannedMealFridayInFirebase(removeMealFromDay.getMealId(), "Wednesday", false, context);
                        mealStoredAtAnyDay(removeMealFromDay);
                        break;
                    case "Thursday":
                        mealDAO.updateThursday(removeMealFromDay.getMealId(), false);
                        removeMealFromDay.setThursday(false);
                        Firebase.updatePlannedMealFridayInFirebase(removeMealFromDay.getMealId(), "Thursday", false, context);
                        mealStoredAtAnyDay(removeMealFromDay);
                        break;
                    case "Friday":
                        mealDAO.updateFriday(removeMealFromDay.getMealId(), false);
                        removeMealFromDay.setFriday(false);
                        Firebase.updatePlannedMealFridayInFirebase(removeMealFromDay.getMealId(), "Friday", false, context);
                        mealStoredAtAnyDay(removeMealFromDay);
                        break;
                }
            }
        }).start();
    }

    public void mealStoredAtAnyDay(PlannedMeals plannedMeal) {
        if (!plannedMeal.isFriday() && !plannedMeal.isThursday() && !plannedMeal.isSaturday() && !plannedMeal.isSunday() && !plannedMeal.isWednesday()
                && !plannedMeal.isTuesday() && !plannedMeal.isMonday()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mealDAO.delete(plannedMeal);
                    Firebase.removePlannedMealFromFirebase(plannedMeal, context);
                }
            }).start();
        }
    }

    public void insertToPlan(PlannedMeals meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insert(meal);
            }
        }).start();
    }

    public void deleteAll() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteAll();
            }
        }).start();
    }


}

