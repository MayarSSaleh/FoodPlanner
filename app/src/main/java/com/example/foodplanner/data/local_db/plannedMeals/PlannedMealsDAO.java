package com.example.foodplanner.data.local_db.plannedMeals;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlannedMealsDAO {
    @Query("SELECT * From plannedMeals_table")
    public LiveData<List<PlannedMeals>> getAllPlannedMeals();

    @Query("SELECT * FROM plannedMeals_table WHERE Saturday =true ")
    public LiveData<List<PlannedMeals>> getSaturdayPlannedMeals();

    @Query("SELECT * FROM plannedMeals_table WHERE Sunday =true ")
    public LiveData<List<PlannedMeals>> getSundayPlannedMeals();

    @Query("SELECT * FROM plannedMeals_table WHERE Monday =true ")
    public LiveData<List<PlannedMeals>> getMondayPlannedMeals();

    @Query("SELECT * FROM plannedMeals_table WHERE Tuesday =true ")
    public LiveData<List<PlannedMeals>> getTuesdayPlannedMeals();

    @Query("SELECT * FROM plannedMeals_table WHERE Wednesday =true ")
    public LiveData<List<PlannedMeals>> getWednesdayPlannedMeals();

    @Query("SELECT * FROM plannedMeals_table WHERE Thursday =true ")
    public LiveData<List<PlannedMeals>> getThursdayPlannedMeals();

    @Query("SELECT * FROM plannedMeals_table WHERE Friday =true ")
    public LiveData<List<PlannedMeals>> getFridayPlannedMeals();

    @Insert
    public void insert(PlannedMeals plannedMeals);

    @Query("UPDATE plannedMeals_table SET Saturday = :sign WHERE mealId = :mealId")
    void updateSaturday(String mealId, boolean sign);

    @Query("UPDATE plannedMeals_table SET Sunday = :sign WHERE mealId = :mealId")
    void updateSunday(String mealId, boolean sign);

    @Query("UPDATE plannedMeals_table SET Monday = :sign WHERE mealId = :mealId")
    void updateMonday(String mealId, boolean sign);

    @Query("UPDATE plannedMeals_table SET Tuesday = :sign WHERE mealId = :mealId")
    void updateTuesday(String mealId, boolean sign);

    @Query("UPDATE plannedMeals_table SET Wednesday = :sign WHERE mealId = :mealId")
    void updateWednesday(String mealId, boolean sign);

    @Query("UPDATE plannedMeals_table SET Thursday = :sign WHERE mealId = :mealId")
    void updateThursday(String mealId, boolean sign);

    @Query("UPDATE plannedMeals_table SET Friday = :sign WHERE mealId = :mealId")
    void updateFriday(String mealId, boolean sign);

    @Delete
    public void delete(PlannedMeals plannedMeals);

}