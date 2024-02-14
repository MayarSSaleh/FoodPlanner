package com.example.foodplanner.data.local_db.favMeals;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface FavMealsDAO {
    @Query("SELECT * From favMeals_table")
//    public LiveData<List<FavMeals>> getAllFavProducts();
    Flowable<List<FavMeals>> getAllFavProducts();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    public void insertToFav(FavMeals favMeals);
 Completable insertToFav(FavMeals favMeals);

    @Delete
//    public void deleteFromFav(FavMeals favMeals);
    Completable deleteFromFav(FavMeals favMeals);
}
