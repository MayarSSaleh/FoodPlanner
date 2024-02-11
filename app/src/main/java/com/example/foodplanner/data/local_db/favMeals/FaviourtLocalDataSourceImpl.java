package com.example.foodplanner.data.local_db.favMeals;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.data.local_db.APPDataBase;

import java.util.List;
// i write imp after the name of calss as we can make interface , but for now we didinot
public class FaviourtLocalDataSourceImpl {
    private static final String TAG = "team";
    private Context context;
    private FavMealsDAO mealDAO;
    private LiveData<List<FavMeals>> storedFvProduct;
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

    public LiveData<List<FavMeals>> getStoredFvProduct() {
        return storedFvProduct;
    }

    public void delete(FavMeals mealCard) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "ON delet respoitory");
                mealDAO.deleteFromFav(mealCard);
            }
        }).start();

    }

    public void insert(FavMeals mealCard) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "ON insert respoitory");

                mealDAO.insertToFav(mealCard);
            }
        }).start();
    }
}
