package com.example.foodplanner.screens.sharedMainActivity.plan.presenter;

import android.content.Context;

import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.screens.sharedMainActivity.plan.view.DayMeals;

import java.util.List;

public interface PlanMealPresenter {
    public List<DayMeals> CollectMealsAcoordingDay( List<PlannedMeals> allMeals);
    public void removeFromPlan(PlannedMeals mealCard , String day, Context context);


}
