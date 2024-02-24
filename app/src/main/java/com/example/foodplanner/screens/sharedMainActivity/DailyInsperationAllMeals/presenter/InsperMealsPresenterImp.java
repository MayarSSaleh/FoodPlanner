package com.example.foodplanner.screens.sharedMainActivity.DailyInsperationAllMeals.MinimizedMealCard.presenter;

import android.util.Log;
import com.example.foodplanner.data.model.MealsRepository;
import com.example.foodplanner.data.model.MealsResponse;
import com.example.foodplanner.screens.sharedMainActivity.DailyInsperationAllMeals.MinimizedMealCard.view.InsperMealsView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class InsperMealsPresenterImp implements InsperMealsPresenter {
    private static final String TAG = "TAG";
    MealsRepository mealsRepository;
    InsperMealsView allMealsView;

    public InsperMealsPresenterImp(MealsRepository mealsRepository, InsperMealsView allMealsView) {
        this.mealsRepository = mealsRepository;
        this.allMealsView = allMealsView;
    }

    @Override
    public void getAllProducts() {
        Observable<MealsResponse> observable = mealsRepository.getRandomMeal();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealsResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("t", "on onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull MealsResponse mealsResponse) {
                        allMealsView.showData(mealsResponse.meals.get(0));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        allMealsView.showErrMsg(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
