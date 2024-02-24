package com.example.foodplanner.data.local_db;
import androidx.room.TypeConverter;
import com.example.foodplanner.data.model.Ingredient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class ConvertersIngredientsList {
    @TypeConverter
    public static List<Ingredient> fromString(String value) {
        Type listType = new TypeToken<List<Ingredient>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<Ingredient> list) {
        return new Gson().toJson(list);
    }
}
