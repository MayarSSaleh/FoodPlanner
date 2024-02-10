package com.example.foodplanner.data.local_db.plannedMeals;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
@Dao
public interface PlannedMealsDAO {
    @Query("SELECT * FROM plannedMeals_table")
    public   LiveData<List<PlannedMeals>> getAllPlannedMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert (PlannedMeals plannedMeals);

    @Delete
    public void delete (PlannedMeals plannedMeals);


}