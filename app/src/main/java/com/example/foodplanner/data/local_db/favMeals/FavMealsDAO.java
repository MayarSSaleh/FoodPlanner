package com.example.foodplanner.data.local_db.favMeals;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.data.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface FavMealsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertToFav(Meal favMeals);

    @Delete
    Completable deleteFromFav(Meal favMeals);

    @Query("DELETE FROM favMeals_table")
    Completable deleteAll();

    @Query("SELECT * FROM favMeals_table ")
    Flowable<List<Meal>> getAllFavProducts();

}
