package com.example.foodplanner.screens.sharedMainActivity.search.Area.Presenter;

import com.example.foodplanner.data.model.MealsRepository;
import com.example.foodplanner.data.model.MealsResponse;
import com.example.foodplanner.screens.sharedMainActivity.search.Area.View.AreaMealsView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class AreaMealsPresenterImp implements AreaMealsPresenter{
    MealsRepository mealsRepository;
    AreaMealsView areaMealsView ;


    public AreaMealsPresenterImp(MealsRepository mealsRepository, AreaMealsView areaMealsViewView ) {
        this.mealsRepository = mealsRepository;
        this.areaMealsView = areaMealsViewView;
    }


    @Override
    public void getAreaMeals(String areaName) {
        Observable<MealsResponse> observable = mealsRepository.getAreaMeals(areaName);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealsResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull MealsResponse mealsResponse) {
//                        Log.d("daaaaaaaaa",":::  "  + mealsResponse.meals.size());
                        areaMealsView.showData(mealsResponse.meals);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        areaMealsView.showErrMsg(e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }



}
