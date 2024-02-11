package com.example.foodplanner.data.model;

import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.data.network.NetworkCallback;

public interface MealsRepository {
    // >>>>>>>>>>>>>>>>>>>>>>>>REMOTE HANDLING<<<<<<<<<<<<<<<<<<<<<<<<<<
    // the following was the first one try to get it from network
    public void getAllInsperMeals(NetworkCallback networkCallback);


    // >>>>>>>>>>>>>>>>>>>>>>>>LOCAL HANDLING<<<<<<<<<<<<<<<<<<<<<<<<<<
    //>>>>>>>>>>>>>>>>>>>>>>...for faviourt<<<<<<<<<
    void insertinFavTable(FavMeals favMeals);

    void deleteFromFav(FavMeals favMeals);

    //>>>>>>>>>>>>>>>>>>>>>>...for plan<<<<<<<<<
    void insertintoPlanTable(PlannedMeals plannedMeal);

    void deleteFromPlanTable(PlannedMeals plannedMeal);

}

