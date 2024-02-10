package com.example.foodplanner.data.local_db.favMeals;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface FavMealsDAO {
    @Query("SELECT * From favMeals_table")
    public LiveData<List<FavMeals>> getAllFavProducts();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertToFav(FavMeals favMeals);

    @Delete
    public void deleteFromFav(FavMeals favMeals);

}
