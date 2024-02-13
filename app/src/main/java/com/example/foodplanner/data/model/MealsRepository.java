package com.example.foodplanner.data.model;

import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.network.NetworkCallback;

import java.util.List;

public interface MealsRepository {
    // >>>>>>>>>>>>>>>>>>>>>>>>REMOTE HANDLING<<<<<<<<<<<<<<<<<<<<<<<<<<
    // the following was the first one try to get it from network
    public void getAllInsperMeals(NetworkCallback networkCallback);


    // >>>>>>>>>>>>>>>>>>>>>>>>LOCAL HANDLING<<<<<<<<<<<<<<<<<<<<<<<<<<
    //>>>>>>>>>>>>>>>>>>>>>>...for faviourt<<<<<<<<<
    void insertinFavTable(FavMeals favMeals);

    void deleteFromFav(FavMeals favMeals);

    //>>>>>>>>>>>>>>>>>>>>>>...for plan<<<<<<<<<
    void insertintoPlanTable(List<PlannedMeals> meals, PlannedMeals plannedMeal , String day);

    void deleteFromPlanTable(PlannedMeals plannedMeal, String day);
}

