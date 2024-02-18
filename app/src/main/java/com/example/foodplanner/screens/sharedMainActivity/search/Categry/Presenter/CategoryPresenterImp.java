package com.example.foodplanner.screens.sharedMainActivity.search.Categry.Presenter;

import com.example.foodplanner.data.model.CategoryResponse;
import com.example.foodplanner.data.model.MealsRepository;
import com.example.foodplanner.screens.sharedMainActivity.search.Categry.View.CategoryView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class CategoryPresenterImp  implements  CategoryPresenter{
    private static final String TAG = "TAG";
    MealsRepository mealsRepository;
    CategoryView categoryView;


    public CategoryPresenterImp(MealsRepository mealsRepository, CategoryView categoryView ) {
        this.mealsRepository = mealsRepository;
        this.categoryView = categoryView;
    }


    @Override
        public void getAllCategories() {
        Observable<CategoryResponse> observable = mealsRepository.getAllCategories();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull CategoryResponse categoryResponse) {
                        categoryView.showData(categoryResponse.categories);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        categoryView.showErrMsg(e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
