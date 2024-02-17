package com.example.foodplanner.data.local_db.favMeals;

import android.content.Context;

import com.example.foodplanner.data.firebase.UpdateFirebase;
import com.example.foodplanner.data.local_db.APPDataBase;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

// i write imp after the name of calss as we can make interface , but for now we didinot
public class FaviourtLocalDataSource {
    private static final String TAG = "team";
    private Context context;
    private FavMealsDAO mealDAO;
    private Flowable<List<FavMeals>> storedFvProduct;
    private static FaviourtLocalDataSource rep = null;

    public FaviourtLocalDataSource(Context context) {
        this.context = context;
        APPDataBase db = APPDataBase.getInstance(context);
        mealDAO = db.getFavMealsDAO();
        storedFvProduct = mealDAO.getAllFavProducts();
    }

    public static FaviourtLocalDataSource getInstance(Context context ) {
        if (rep == null) {
            rep = new FaviourtLocalDataSource(context );
        }
        return rep;
    }

    public Flowable<List<FavMeals>> getStoredFvProduct() {
        return mealDAO.getAllFavProducts();
    }

    public Completable delete(FavMeals mealCard) {
        return mealDAO.deleteFromFav(mealCard);
    }

    public Completable insert(FavMeals mealCard) {
        return mealDAO.insertToFav(mealCard);
    }
}
