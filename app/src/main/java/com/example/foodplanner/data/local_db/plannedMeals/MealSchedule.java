package com.example.foodplanner.data.local_db.plannedMeals;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;


@Entity(tableName = "MealSchedules",
        primaryKeys = {"weekday", "mealId"},
        foreignKeys = @ForeignKey(entity = PlannedMeals.class,
                parentColumns = "mealId",
                childColumns = "mealId",
                onDelete = ForeignKey.CASCADE))
public class MealSchedule {
    @NonNull
    private String weekday;
    @NonNull
    private String mealId;

    public MealSchedule(@NonNull String weekday, @NonNull String mealId) {
        this.weekday = weekday;
        this.mealId = mealId;
    }

    @NonNull
    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(@NonNull String weekday) {
        this.weekday = weekday;
    }

    @NonNull
    public String getMealId() {
        return mealId;
    }

    public void setMealId(@NonNull String mealId) {
        this.mealId = mealId;
    }
}
