package com.example.foodplanner.screens.sharedMainActivity.search.Categry.Presenter;

import com.example.foodplanner.data.model.MealsRepository;
import com.example.foodplanner.data.model.MealsResponse;
import com.example.foodplanner.screens.sharedMainActivity.search.Categry.View.CategoryMealsView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class CategoryMealsPresenterImp implements  CategoryMealsPresenter{
    MealsRepository mealsRepository;
    CategoryMealsView categoryMealsView;


    public CategoryMealsPresenterImp(MealsRepository mealsRepository, CategoryMealsView categoryMealsView ) {
        this.mealsRepository = mealsRepository;
        this.categoryMealsView = categoryMealsView;
    }


    @Override
    public void getCategoryMeals(String categoryName) {
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


                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }




}
