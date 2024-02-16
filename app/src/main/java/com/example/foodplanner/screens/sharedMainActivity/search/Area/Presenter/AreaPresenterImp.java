package com.example.foodplanner.screens.sharedMainActivity.search.Area.Presenter;

import com.example.foodplanner.data.model.AreaResponse;
import com.example.foodplanner.data.model.CategoryResponse;
import com.example.foodplanner.data.model.MealsRepository;
import com.example.foodplanner.screens.sharedMainActivity.search.Area.View.AreaView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class AreaPresenterImp implements AreaPresenter {
    MealsRepository mealsRepository;
    AreaView areaView;


    public AreaPresenterImp(MealsRepository mealsRepository, AreaView areaView) {
        this.mealsRepository = mealsRepository;
        this.areaView = areaView;
    }

    @Override
    public void getAreas() {
        Observable<AreaResponse> observable = mealsRepository.getAreas();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AreaResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AreaResponse areaResponse) {
                        areaView.showData(areaResponse.meals);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        areaView.showErrMsg(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
