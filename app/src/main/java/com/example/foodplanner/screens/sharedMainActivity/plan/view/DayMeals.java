package com.example.foodplanner.screens.sharedMainActivity.plan.view;

import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;

import java.util.List;

public class DayMeals {
    String day;
    List<PlannedMeals> listOfMeals;

    public DayMeals(String day, List<PlannedMeals> listOfMeals) {
        this.day = day;
        this.listOfMeals = listOfMeals;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<PlannedMeals> getListOfMeals() {
        return listOfMeals;
    }

    public void setListOfMeals(List<PlannedMeals> listOfMeals) {
        this.listOfMeals = listOfMeals;
    }
}
