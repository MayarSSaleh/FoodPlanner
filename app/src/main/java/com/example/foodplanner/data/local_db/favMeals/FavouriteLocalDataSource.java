package com.example.foodplanner.data.local_db.favMeals;

import android.content.Context;

import com.example.foodplanner.data.local_db.APPDataBase;
import com.example.foodplanner.data.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class FavouriteLocalDataSource {

    private Context context;
    private FavMealsDAO mealDAO;
    private Flowable<List<Meal>> storedFvProduct;
    private static FavouriteLocalDataSource rep = null;

    public FavouriteLocalDataSource(Context context) {
        this.context = context;
        APPDataBase db = APPDataBase.getInstance(context);
        mealDAO = db.getFavMealsDAO();
        storedFvProduct = mealDAO.getAllFavProducts();
    }

    public static FavouriteLocalDataSource getInstance(Context context) {
        if (rep == null) {
            rep = new FavouriteLocalDataSource(context);
        }
        return rep;
    }

    public Flowable<List<Meal>> getStoredFvProduct() {
        return mealDAO.getAllFavProducts();
    }

    public Completable delete(Meal mealCard) {
        return mealDAO.deleteFromFav(mealCard);
    }

    public Completable deleteAll() {
        return mealDAO.deleteAll();
    }

    public Completable insert(Meal mealCard) {
        return mealDAO.insertToFav(mealCard);
    }

}
