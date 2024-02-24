package com.example.foodplanner.screens.sharedMainActivity.favMeals.presenter;

import com.example.foodplanner.data.model.Meal;
import java.util.List;
import io.reactivex.rxjava3.core.Flowable;

public interface FavMealsPresenter {

    Flowable<List<Meal>> getStoredFvProduct();
}
