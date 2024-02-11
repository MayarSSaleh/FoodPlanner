package com.example.foodplanner.screens.sharedMainActivity.favMeals.favList.presenter;

import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.model.MealsRepositoryImpl;

public class FavMealsPresnterImp implements FavMealsPresenter {
    MealsRepositoryImpl repos;

    public FavMealsPresnterImp( MealsRepositoryImpl repos) {
        this.repos = repos;
    }

    public void delete(FavMeals favMeal) {
        repos.deleteFromFav(favMeal);
    }

}
