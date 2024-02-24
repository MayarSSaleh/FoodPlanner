package com.example.foodplanner.data.local_db.plannedMeals;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.foodplanner.data.local_db.ConvertMeal;
import com.example.foodplanner.data.model.Meal;

import java.io.Serializable;

@Entity(tableName = "plannedMeals_table")
@TypeConverters(ConvertMeal.class)
public class PlannedMeals implements Serializable {

    @PrimaryKey
    @NonNull
    String mealId;
    Meal plannedMeal;
    boolean Saturday, Monday, Tuesday, Wednesday, Thursday, Sunday, Friday;

    public PlannedMeals() {
    }


    @NonNull
    public String getMealId() {
        return mealId;
    }

    public void setMealId(@NonNull String mealId) {
        this.mealId = mealId;
    }

    public Meal getPlannedMeal() {
        return plannedMeal;
    }

    public void setPlannedMeal(Meal plannedMeal) {
        this.plannedMeal = plannedMeal;
    }

    public boolean isSaturday() {
        return Saturday;
    }

    public void setSaturday(boolean saturday) {
        Saturday = saturday;
    }

    public boolean isMonday() {
        return Monday;
    }

    public void setMonday(boolean monday) {
        Monday = monday;
    }

    public boolean isTuesday() {
        return Tuesday;
    }

    public void setTuesday(boolean tuesday) {
        Tuesday = tuesday;
    }

    public boolean isWednesday() {
        return Wednesday;
    }

    public void setWednesday(boolean wednesday) {
        Wednesday = wednesday;
    }

    public boolean isThursday() {
        return Thursday;
    }

    public void setThursday(boolean thursday) {
        Thursday = thursday;
    }

    public boolean isSunday() {
        return Sunday;
    }

    public void setSunday(boolean sunday) {
        Sunday = sunday;
    }

    public boolean isFriday() {
        return Friday;
    }

    public void setFriday(boolean friday) {
        Friday = friday;
    }
}

