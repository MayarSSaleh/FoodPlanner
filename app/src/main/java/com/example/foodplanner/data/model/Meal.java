package com.example.foodplanner.data.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.foodplanner.data.local_db.ConvertersIngredientsList;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "favMeals_table")
@TypeConverters(ConvertersIngredientsList.class)
public class Meal implements Serializable {

    @PrimaryKey
    @NonNull
    @SerializedName("idMeal")
    String mealId;
    @SerializedName("strMeal")
    String name;
    @SerializedName("strMealThumb")
    String photourl;
    @SerializedName("strArea")
    String country;
    @SerializedName("strYoutube")
    String videoUrl;
    @SerializedName("strIngredient1")
    String ingr1;
    @SerializedName("strIngredient2")
    String ingr2;
    @SerializedName("strIngredient3")
    String ingr3;
    @SerializedName("strIngredient4")
    String ingr4;
    @SerializedName("strIngredient5")
    String ingr5;
    @SerializedName("strIngredient6")
    String ingr6;
    @SerializedName("strIngredient7")
    String ingr7;
    @SerializedName("strIngredient8")
    String ingr8;
    @SerializedName("strIngredient9")
    String ingr9;
    @SerializedName("strIngredient10")
    String ingr10;
    @SerializedName("strIngredient11")
    String ingr11;
    @SerializedName("strIngredient12")
    String ingr12;
    @SerializedName("strIngredient13")
    String ingr13;
    @SerializedName("strIngredient14")
    String ingr14;
    @SerializedName("strIngredient15")
    String ingr15;
    @SerializedName("strIngredient16")
    String ingr16;
    @SerializedName("strIngredient17")
    String ingr17;
    @SerializedName("strIngredient18")
    String ingr18;
    @SerializedName("strIngredient19")
    String ingr19;
    @SerializedName("strIngredient20")
    String ingr20;
    @SerializedName("strMeasure1")
    String ingr1m;
    @SerializedName("strMeasure2")
    String ingr2m;
    @SerializedName("strMeasure3")
    String ingr3m;
    @SerializedName("strMeasure4")
    String ingr4m;
    @SerializedName("strMeasure5")
    String ingr5m;
    @SerializedName("strMeasure6")
    String ingr6m;
    @SerializedName("strMeasure7")
    String ingr7m;
    @SerializedName("strMeasure8")
    String ingr8m;
    @SerializedName("strMeasure9")
    String ingr9m;
    @SerializedName("strMeasure10")
    String ingr10m;
    @SerializedName("strMeasure11")
    String ingr11m;
    @SerializedName("strMeasure12")
    String ingr12m;
    @SerializedName("strMeasure13")
    String ingr13m;
    @SerializedName("strMeasure14")
    String ingr14m;
    @SerializedName("strMeasure15")
    String ingr15m;
    @SerializedName("strMeasure16")
    String ingr16m;
    @SerializedName("strMeasure17")
    String ingr17m;
    @SerializedName("strMeasure18")
    String ingr18m;
    @SerializedName("strMeasure19")
    String ingr19m;
    @SerializedName("strMeasure20")
    String ingr20m;
    @SerializedName("strInstructions")
    String steps;

// check if empty ,so it is new meal
    List<Ingredient> allIngredient = new ArrayList<>();
    boolean isFav;

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public Meal() {
    }

    public List<Ingredient> getAllIngredient() {
        return allIngredient;
    }

    public void setAllIngredient(List<Ingredient> allIngredient) {
        this.allIngredient = allIngredient;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
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

    public String getIngr1() {
        return ingr1;
    }

    public void setIngr1(String ingr1) {
        this.ingr1 = ingr1;
    }

    public String getIngr2() {
        return ingr2;
    }

    public void setIngr2(String ingr2) {
        this.ingr2 = ingr2;
    }

    public String getIngr3() {
        return ingr3;
    }

    public void setIngr3(String ingr3) {
        this.ingr3 = ingr3;
    }

    public String getIngr4() {
        return ingr4;
    }

    public void setIngr4(String ingr4) {
        this.ingr4 = ingr4;
    }

    public String getIngr5() {
        return ingr5;
    }

    public void setIngr5(String ingr5) {
        this.ingr5 = ingr5;
    }

    public String getIngr6() {
        return ingr6;
    }

    public void setIngr6(String ingr6) {
        this.ingr6 = ingr6;
    }

    public String getIngr7() {
        return ingr7;
    }

    public void setIngr7(String ingr7) {
        this.ingr7 = ingr7;
    }

    public String getIngr8() {
        return ingr8;
    }

    public void setIngr8(String ingr8) {
        this.ingr8 = ingr8;
    }

    public String getIngr9() {
        return ingr9;
    }

    public void setIngr9(String ingr9) {
        this.ingr9 = ingr9;
    }

    public String getIngr10() {
        return ingr10;
    }

    public void setIngr10(String ingr10) {
        this.ingr10 = ingr10;
    }

    public String getIngr11() {
        return ingr11;
    }

    public void setIngr11(String ingr11) {
        this.ingr11 = ingr11;
    }

    public String getIngr12() {
        return ingr12;
    }

    public void setIngr12(String ingr12) {
        this.ingr12 = ingr12;
    }

    public String getIngr13() {
        return ingr13;
    }

    public void setIngr13(String ingr13) {
        this.ingr13 = ingr13;
    }

    public String getIngr14() {
        return ingr14;
    }

    public void setIngr14(String ingr14) {
        this.ingr14 = ingr14;
    }

    public String getIngr15() {
        return ingr15;
    }

    public void setIngr15(String ingr15) {
        this.ingr15 = ingr15;
    }

    public String getIngr16() {
        return ingr16;
    }

    public void setIngr16(String ingr16) {
        this.ingr16 = ingr16;
    }

    public String getIngr17() {
        return ingr17;
    }

    public void setIngr17(String ingr17) {
        this.ingr17 = ingr17;
    }

    public String getIngr18() {
        return ingr18;
    }

    public void setIngr18(String ingr18) {
        this.ingr18 = ingr18;
    }

    public String getIngr19() {
        return ingr19;
    }

    public void setIngr19(String ingr19) {
        this.ingr19 = ingr19;
    }

    public String getIngr20() {
        return ingr20;
    }

    public void setIngr20(String ingr20) {
        this.ingr20 = ingr20;
    }

    public String getIngr1m() {
        return ingr1m;
    }

    public void setIngr1m(String ingr1m) {
        this.ingr1m = ingr1m;
    }

    public String getIngr2m() {
        return ingr2m;
    }

    public void setIngr2m(String ingr2m) {
        this.ingr2m = ingr2m;
    }

    public String getIngr3m() {
        return ingr3m;
    }

    public void setIngr3m(String ingr3m) {
        this.ingr3m = ingr3m;
    }

    public String getIngr4m() {
        return ingr4m;
    }

    public void setIngr4m(String ingr4m) {
        this.ingr4m = ingr4m;
    }

    public String getIngr5m() {
        return ingr5m;
    }

    public void setIngr5m(String ingr5m) {
        this.ingr5m = ingr5m;
    }

    public String getIngr6m() {
        return ingr6m;
    }

    public void setIngr6m(String ingr6m) {
        this.ingr6m = ingr6m;
    }

    public String getIngr7m() {
        return ingr7m;
    }

    public void setIngr7m(String ingr7m) {
        this.ingr7m = ingr7m;
    }

    public String getIngr8m() {
        return ingr8m;
    }

    public void setIngr8m(String ingr8m) {
        this.ingr8m = ingr8m;
    }

    public String getIngr9m() {
        return ingr9m;
    }

    public void setIngr9m(String ingr9m) {
        this.ingr9m = ingr9m;
    }

    public String getIngr10m() {
        return ingr10m;
    }

    public void setIngr10m(String ingr10m) {
        this.ingr10m = ingr10m;
    }

    public String getIngr11m() {
        return ingr11m;
    }

    public void setIngr11m(String ingr11m) {
        this.ingr11m = ingr11m;
    }

    public String getIngr12m() {
        return ingr12m;
    }

    public void setIngr12m(String ingr12m) {
        this.ingr12m = ingr12m;
    }

    public String getIngr13m() {
        return ingr13m;
    }

    public void setIngr13m(String ingr13m) {
        this.ingr13m = ingr13m;
    }

    public String getIngr14m() {
        return ingr14m;
    }

    public void setIngr14m(String ingr14m) {
        this.ingr14m = ingr14m;
    }

    public String getIngr15m() {
        return ingr15m;
    }

    public void setIngr15m(String ingr15m) {
        this.ingr15m = ingr15m;
    }

    public String getIngr16m() {
        return ingr16m;
    }

    public void setIngr16m(String ingr16m) {
        this.ingr16m = ingr16m;
    }

    public String getIngr17m() {
        return ingr17m;
    }

    public void setIngr17m(String ingr17m) {
        this.ingr17m = ingr17m;
    }

    public String getIngr18m() {
        return ingr18m;
    }

    public void setIngr18m(String ingr18m) {
        this.ingr18m = ingr18m;
    }

    public String getIngr19m() {
        return ingr19m;
    }

    public void setIngr19m(String ingr19m) {
        this.ingr19m = ingr19m;
    }

    public String getIngr20m() {
        return ingr20m;
    }

    public void setIngr20m(String ingr20m) {
        this.ingr20m = ingr20m;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

}

