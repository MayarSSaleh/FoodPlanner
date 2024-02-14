package com.example.foodplanner.data.local_db.favMeals;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.data.local_db.APPDataBase;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

// i write imp after the name of calss as we can make interface , but for now we didinot
public class FaviourtLocalDataSourceImpl {
    private static final String TAG = "team";
    private Context context;
    private FavMealsDAO mealDAO;
    private Flowable<List<FavMeals>> storedFvProduct;
    private static FaviourtLocalDataSourceImpl rep = null;

    public FaviourtLocalDataSourceImpl(Context context) {
        this.context = context;
        APPDataBase db = APPDataBase.getInstance(context);
        mealDAO = db.getFavMealsDAO();
        storedFvProduct = mealDAO.getAllFavProducts();
    }

    public static FaviourtLocalDataSourceImpl getInstance(Context context) {
        if (rep == null) {
            rep = new FaviourtLocalDataSourceImpl(context);
        }
        return rep;
    }


    public Flowable<List<FavMeals>> getStoredFvProduct() {
        return mealDAO.getAllFavProducts();   }

    public Completable delete(FavMeals mealCard) {
        return mealDAO.deleteFromFav(mealCard);
    }

    public Completable insert(FavMeals mealCard) {
        return mealDAO.insertToFav(mealCard);
    }

//    public void delete(FavMeals mealCard) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.i(TAG, "ON delet respoitory");
//                mealDAO.deleteFromFav(mealCard);
//            }
//        }).start();
//    }
//
//    public void insert(FavMeals mealCard) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.i(TAG, "ON insert respoitory");
//                mealDAO.insertToFav(mealCard);
//            }
//        }).start();
//    }
}
