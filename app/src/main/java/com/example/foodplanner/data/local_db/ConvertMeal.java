package com.example.foodplanner.data.local_db;

import androidx.room.TypeConverter;
import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.Meal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class ConvertMeal {
    @TypeConverter
    public static Meal fromString(String value) {
        return new Gson().fromJson(value, Meal.class);
    }
    @TypeConverter
    public static String fromList(Meal meal) {
        return new Gson().toJson(meal);
    }


}
