package com.example.foodplanner.screens.sharedMainActivity.favMeals.favList.presenter;

import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.data.model.MealsRepositoryImpl;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class FavMealsPresnterImp implements FavMealsPresenter {
    MealsRepositoryImpl repos;

    public FavMealsPresnterImp(MealsRepositoryImpl repos) {
        this.repos = repos;
    }

    @Override
    public Flowable<List<FavMeals>> getStoredFvProduct() {
        return repos.getStoredFvProduct( );
    }

}
