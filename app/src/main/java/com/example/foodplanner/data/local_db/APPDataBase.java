package com.example.foodplanner.data.local_db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.favMeals.FavMealsDAO;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMealsDAO;
@Database(entities={FavMeals.class, PlannedMeals.class}, version = 1)

public abstract class APPDataBase extends RoomDatabase {
    private static APPDataBase instance = null;
    //    In summary, the getFavMealsDAO() method in the APPDataBase class
//    is crucial for obtaining an instance of the FavMealsDAO interface,
//    which encapsulates database access operations for the FavMeals table,
//    promotes code organization, and facilitates dependency injection.
    public abstract FavMealsDAO getFavMealsDAO();
    public abstract PlannedMealsDAO getPlannedMealsDAO();
    public static synchronized APPDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), APPDataBase.class, "mealsdb").build();
        }
        return instance;
    }
}
