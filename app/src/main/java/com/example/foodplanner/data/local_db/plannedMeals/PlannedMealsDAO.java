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
//@Dao
//public interface MealDao {
//    @Insert
//    void insert(Meal meal);
//
//    @Update
//    void update(Meal meal);
//
//    @Delete
//    void delete(Meal meal);
//
//    @Query("SELECT * FROM Meals")
//    LiveData<List<Meal>> getAllMeals();
//}
//
//@Dao
//public interface MealScheduleDao {
//    @Insert
//    void insert(MealSchedule mealSchedule);
//
//    @Update
//    void update(MealSchedule mealSchedule);
//
//    @Delete
//    void delete(MealSchedule mealSchedule);
//
//    @Query("SELECT * FROM MealSchedules WHERE weekday = :weekday")
//    LiveData<List<MealSchedule>> getMealsForWeekday(String weekday);
//}

//used by
//Meal mondayMeal = ...; // Retrieve the meal for Monday
//
//// Create a new Schedule object for Monday and populate it with the meal data
//        Schedule mondaySchedule = new Schedule();
//        mondaySchedule.setDayOfWeek("Monday");
//        mondaySchedule.setMeal(mondayMeal);
//
//// Insert the populated Schedule object into the database
//        scheduleDao.insertSchedule(mondaySchedule);

//retrive by
//// Get an instance of your DAO class
//ScheduleDao scheduleDao = yourDatabaseInstance.getScheduleDao();
//
//// Use the DAO method to query the database for meals scheduled for Monday
//List<Schedule> mondayMeals = scheduleDao.getMealsByDayOfWeek(Calendar.MONDAY);
//
//// Handle the returned data
//for (Schedule schedule : mondayMeals) {
//    // Do something with each scheduled meal
//}