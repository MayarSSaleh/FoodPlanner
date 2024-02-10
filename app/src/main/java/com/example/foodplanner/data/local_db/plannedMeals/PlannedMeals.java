package com.example.foodplanner.data.local_db.plannedMeals;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.foodplanner.data.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "plannedMeals_table")
public class PlannedMeals {
    @PrimaryKey
    @NonNull
    String mealId;
    String name;
    @NonNull
    String day;
    String photourl;
    String country;
    String videoUrl;
    List<Ingredient> allingredient;
    String steps;
    boolean isFav;
    boolean isinplan;    //if click add to plan button make value = true and opposite

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public List<Ingredient> getAllingredient() {
        return allingredient;
    }

    public void setAllingredient(List<Ingredient> allingredient) {
        this.allingredient = allingredient;
    }

    @NonNull
    public String getDay() {
        return day;
    }

    public void setDay(@NonNull String day) {
        this.day = day;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public boolean isIsinplan() {
        return isinplan;
    }

    public void setIsinplan(boolean isinplan) {
        this.isinplan = isinplan;
    }

    public PlannedMeals() {
    }
}

