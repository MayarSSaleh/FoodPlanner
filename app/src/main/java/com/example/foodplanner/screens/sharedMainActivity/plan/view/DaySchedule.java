package com.example.foodplanner.screens.sharedMainActivity.plan.view;

import com.example.foodplanner.data.model.MealCard;
import java.util.List;

public class DaySchedule {
    private int dayOfWeek;

    public DaySchedule() {
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<MealCard> getMeals() {
        return meals;
    }

    public void setMeals(List<MealCard> meals) {
        this.meals = meals;
    }

    private List<MealCard> meals;
}

