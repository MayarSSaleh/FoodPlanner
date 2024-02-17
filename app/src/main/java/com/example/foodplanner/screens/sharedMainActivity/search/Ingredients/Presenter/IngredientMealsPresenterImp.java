package com.example.foodplanner.screens.sharedMainActivity.search.Ingredients.Presenter;

import android.util.Log;

import com.example.foodplanner.data.model.MealsRepository;
import com.example.foodplanner.data.model.MealsResponse;
import com.example.foodplanner.screens.sharedMainActivity.search.Categry.View.CategoryMealsView;
import com.example.foodplanner.screens.sharedMainActivity.search.Ingredients.View.IngredientMealsView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class IngredientMealsPresenterImp implements IngredientMealsPresenter {
    MealsRepository mealsRepository;
    IngredientMealsView  ingredientMealsView;



    public IngredientMealsPresenterImp(MealsRepository mealsRepository, IngredientMealsView ingredientMealsView ) {
        this.mealsRepository = mealsRepository;
        this.ingredientMealsView = ingredientMealsView;
    }


    @Override
    public void getIngredientMeals(String ingName) {
        Observable<MealsResponse> observable = mealsRepository.getMealsByIngredient(ingName);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealsResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull MealsResponse mealsResponse) {
                        if (mealsResponse.meals != null) {
                            ingredientMealsView.showData(mealsResponse.meals);
                        } else {
                            ingredientMealsView.showErrMsg("Sorry , there is no meals by this ingredient");
                        }
                    }


                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("d",":::  "  +e.getMessage());

                        ingredientMealsView.showErrMsg(e.getMessage());

                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
