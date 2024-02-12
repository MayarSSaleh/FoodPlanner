package com.example.foodplanner.data.local_db.favMeals;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.foodplanner.data.local_db.Converters;
import com.example.foodplanner.data.model.Ingredient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "favMeals_table")
@TypeConverters(Converters.class)

public class FavMeals implements Serializable {

    @PrimaryKey
    @NonNull
    String mealId;

    String name;
    String photourl;
    String country;
    String videoUrl;
    List<Ingredient> allingredient = new ArrayList<>();
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


    public boolean isIsinplan() {
        return isinplan;
    }

    public void setIsinplan(boolean isinplan) {
        this.isinplan = isinplan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public FavMeals() {
    }

}

