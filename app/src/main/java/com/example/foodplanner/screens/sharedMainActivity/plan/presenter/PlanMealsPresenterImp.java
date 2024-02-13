package com.example.foodplanner.screens.sharedMainActivity.plan.presenter;

import android.content.Context;
import android.util.Log;

import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSourceImpl;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedLocalDataSourceImpl;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.data.network.ProductRemoteDataSourceImpl;
import com.example.foodplanner.screens.sharedMainActivity.plan.view.DayMeals;

import java.util.ArrayList;
import java.util.List;

public class PlanMealsPresenterImp implements PlanMealPresenter {

    List<DayMeals> dayMeals;
    List<PlannedMeals> SaturdayMeals;
    List<PlannedMeals> SundayallMeals;
    List<PlannedMeals> MondayallMeals;
    List<PlannedMeals> TuesdayallMeals;
    List<PlannedMeals> WednesdayallMeals;
    List<PlannedMeals> ThursdayallMeals;
    List<PlannedMeals> FridayAllMeals;
    MealsRepositoryImpl repository;
    ProductRemoteDataSourceImpl productRemoteDataSource;
    FaviourtLocalDataSourceImpl prodcutsLocalDataSource;
    PlannedLocalDataSourceImpl plannedLocalDataSource;


    @Override
    public List<DayMeals> CollectMealsAcoordingDay(List<PlannedMeals> allMeals) {
        dayMeals=new ArrayList<>();
        SaturdayMeals=new ArrayList<>();
        SundayallMeals=new ArrayList<>();
        MondayallMeals=new ArrayList<>();
        TuesdayallMeals=new ArrayList<>();
        WednesdayallMeals=new ArrayList<>();
        ThursdayallMeals=new ArrayList<>();
        FridayAllMeals=new ArrayList<>();
        String TAG="TAG";
        Log.i(TAG, "CollectMealsAcoordingDay:allMeals SIZE "+ allMeals.size());

        for (PlannedMeals meal : allMeals) {
            if (meal.isSaturday()) {
                SaturdayMeals.add(meal);
            }
            if (meal.isSunday()) {
                SundayallMeals.add(meal);
            }
            if (meal.isMonday()) {
                MondayallMeals.add(meal);
            }
            if (meal.isTuesday()) {
                TuesdayallMeals.add(meal);
            }
            if (meal.isWednesday()) {
                WednesdayallMeals.add(meal);
            }
            if (meal.isThursday()) {
                ThursdayallMeals.add(meal);
            }
            if (meal.isFriday()) {
                FridayAllMeals.add(meal);
            }
        }
        dayMeals.add(new DayMeals("Saturday", SaturdayMeals));
        dayMeals.add(new DayMeals("Sunday", SundayallMeals));
        dayMeals.add(new DayMeals("Monday", MondayallMeals));
        dayMeals.add(new DayMeals("Tuesday", TuesdayallMeals));
        dayMeals.add(new DayMeals("Wednesday", WednesdayallMeals));
        dayMeals.add(new DayMeals("Thursday", ThursdayallMeals));
        dayMeals.add(new DayMeals("Friday", FridayAllMeals));
        return dayMeals;
    }

    @Override
    public void removeFromPlan(PlannedMeals meal, String day, Context context) {
        Log.d("TAG, ","onClick: remove in plan presenert");

        productRemoteDataSource = new ProductRemoteDataSourceImpl();
        prodcutsLocalDataSource = new FaviourtLocalDataSourceImpl(context);
        plannedLocalDataSource = new PlannedLocalDataSourceImpl(context);
        repository = MealsRepositoryImpl.getInstance(productRemoteDataSource, prodcutsLocalDataSource, plannedLocalDataSource);
        repository.deleteFromPlanTable(meal,day);
    }
}
