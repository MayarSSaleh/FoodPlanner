package com.example.foodplanner.data.network;

import android.util.Log;

import com.example.foodplanner.data.model.AreaResponse;
import com.example.foodplanner.data.model.CategoryResponse;
import com.example.foodplanner.data.model.MealsResponse;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductRemoteDataSourceImpl {
    private static final String TAG = "team";
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    MealsService pService;

    public ProductRemoteDataSourceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(BASE_URL).build();
        pService = retrofit.create(MealsService.class);
    }

    public Observable<MealsResponse> makeNetworkCall() {
        return pService.getMeal()
                .subscribeOn(Schedulers.io());
    }

    public Observable<CategoryResponse> getCategories() {
        return pService.getCategories()
                .subscribeOn(Schedulers.io());
    }

    public Observable<MealsResponse> getMealcategories(String categoryName) {
        return pService.getCategoryMeals(categoryName)
                .subscribeOn(Schedulers.io());
    }

    public Observable<MealsResponse> getMealDetails(String MealName) {
        Log.d("mmmmmmmmmm", "remote imp  " +MealName );
        return pService.getMealDetails(MealName)
                .subscribeOn(Schedulers.io());
    }

    public Observable<AreaResponse> getAreas() {
        return pService.getAreas()
                .subscribeOn(Schedulers.io());
    }

    public Observable<MealsResponse> getAreaMeals(String areaName) {
        return pService.getAreaMeals(areaName)
                .subscribeOn(Schedulers.io());
    }



}

