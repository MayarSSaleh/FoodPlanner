package com.example.foodplanner.data.local_db.plannedMeals;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import com.example.foodplanner.data.local_db.APPDataBase;
import java.util.List;
// i write imp after the name of calss as we can make interface , but for now we didinot
public class PlannedLocalDataSourceImpl {
    private static final String TAG = "team";
    private Context context;
    private PlannedMealsDAO mealDAO;
    private LiveData<List<PlannedMeals>> storedPlannedMeals;
    private static com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSourceImpl rep = null;

    public PlannedLocalDataSourceImpl(Context context) {
        this.context = context;
        APPDataBase db = APPDataBase.getInstance(context);
        mealDAO = db.getPlannedMealsDAO();
        storedPlannedMeals = mealDAO.getAllPlannedMeals();
    }

    public static com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSourceImpl getInstance(Context context) {
        if (rep == null) {
            rep = new com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSourceImpl(context);
        }
        return rep;
    }

    public LiveData<List<PlannedMeals>> getStoredPlannedMeals() {
        return storedPlannedMeals;
    }

    public void delete(PlannedMeals plannedMeal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.delete(plannedMeal);
            }
        }).start();
    }

    public void insert(PlannedMeals plannedMeal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "ON insert planned meal planed local db");
                mealDAO.insert(plannedMeal);
            }
        }).start();
    }
}
