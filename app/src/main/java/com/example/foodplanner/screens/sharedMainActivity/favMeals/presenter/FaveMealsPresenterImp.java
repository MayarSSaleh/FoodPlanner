package com.example.foodplanner.screens.sharedMainActivity.favMeals.presenter;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.model.MealsRepositoryImpl;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class FaveMealsPresenterImp implements FavMealsPresenter {
    MealsRepositoryImpl repos;

    public FaveMealsPresenterImp(MealsRepositoryImpl repos) {
        this.repos = repos;
    }

    @Override
    public Flowable<List<Meal>> getStoredFvProduct() {
        return repos.getStoredFvProduct( );
    }

}
