package com.example.foodplanner.screens.sharedMainActivity.favMeals.favList.presenter;

import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.model.MealsRepositoryImpl;
import com.example.foodplanner.screens.sharedMainActivity.favMeals.favList.view.FavMealsView;

public class FavMealsPresnterImp implements FavMealsPresenter {
    FavMealsView view;
    MealsRepositoryImpl repos;

    public FavMealsPresnterImp(FavMealsView view, MealsRepositoryImpl repos) {
        this.view = view;
        this.repos = repos;
    }

    public void delete(FavMeals favMeal) {
        repos.deleteFromFav(favMeal);
    }

}
