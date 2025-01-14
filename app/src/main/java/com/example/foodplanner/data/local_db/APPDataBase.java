package com.example.foodplanner.data.local_db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.foodplanner.data.local_db.favMeals.FavMealsDAO;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMealsDAO;
import com.example.foodplanner.data.model.Meal;

@Database(entities={Meal.class, PlannedMeals.class}, version = 1)
@TypeConverters({ConvertersIngredientsList.class, ConvertMeal.class})

public abstract class APPDataBase extends RoomDatabase {
    private static APPDataBase instance = null;

    public abstract FavMealsDAO getFavMealsDAO();
    public abstract PlannedMealsDAO getPlannedMealsDAO();


    public static synchronized APPDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), APPDataBase.class, "mealsdb")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}


