package com.example.foodplanner.screens.sharedMainActivity.search.Ingredients.Presenter;

import com.example.foodplanner.data.model.MealsRepository;
import com.example.foodplanner.data.model.MealsResponse;
import com.example.foodplanner.screens.sharedMainActivity.search.Categry.View.CategoryMealsView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class IngredientMealsPresenterImp implements IngredientMealsPresenter {
    MealsRepository mealsRepository;
    CategoryMealsView categoryMealsView;


    public IngredientMealsPresenterImp(MealsRepository mealsRepository, CategoryMealsView categoryMealsView ) {
        this.mealsRepository = mealsRepository;
        this.categoryMealsView = categoryMealsView;
    }


    @Override
    public void getIngredientMeals(String categoryName) {
        Observable<MealsResponse> observable = mealsRepository.getCatgoryMeals(categoryName);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealsResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull MealsResponse mealsResponse) {
//                        Log.d("daaaaaaaaa",":::  "  + mealsResponse.meals.size());
                        categoryMealsView.showData(mealsResponse.meals);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        categoryMealsView.showErrMsg(e.getMessage());

                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
