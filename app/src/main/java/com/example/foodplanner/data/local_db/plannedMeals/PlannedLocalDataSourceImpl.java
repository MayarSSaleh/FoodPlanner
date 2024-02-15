package com.example.foodplanner.data.local_db.plannedMeals;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.data.local_db.APPDataBase;
import com.example.foodplanner.data.local_db.favMeals.FaviourtLocalDataSource;

import java.util.List;


// i write imp after the name of calss as we can make interface , but for now we didinot
public class PlannedLocalDataSourceImpl {
    private static final String TAG = "team";
    private Context context;
    private PlannedMealsDAO mealDAO;
    private LiveData<List<PlannedMeals>> storedStaPlannedMeals;
    private LiveData<List<PlannedMeals>> storedSunPlannedMeals;
    private LiveData<List<PlannedMeals>> storedMonPlannedMeals;
    private LiveData<List<PlannedMeals>> storedPlannedMeals;
    boolean Saturday, Monday, Tuesday, Wednesday, Thursday, Sunday, Friday;

//    private LiveData<List<PlannedMeals>> storedPlannedMeals;
//    private LiveData<List<PlannedMeals>> storedPlannedMeals;
//    private LiveData<List<PlannedMeals>> storedPlannedMeals;
//    private LiveData<List<PlannedMeals>> storedPlannedMeals;

    private static FaviourtLocalDataSource rep = null;

    public PlannedLocalDataSourceImpl(Context context) {
        this.context = context;
        APPDataBase db = APPDataBase.getInstance(context);
        mealDAO = db.getPlannedMealsDAO();
        storedPlannedMeals = mealDAO.getAllPlannedMeals();

    }

    public static FaviourtLocalDataSource getInstance(Context context) {
        if (rep == null) {
            rep = new FaviourtLocalDataSource(context);
        }
        return rep;
    }

    public LiveData<List<PlannedMeals>> getStoredPlannedMeals() {
        return storedPlannedMeals;
    }



//    public LiveData<List<PlannedMeals>> getSaturdayPlannedMeals() {
//    }
//
//    public LiveData<List<PlannedMeals>> getSaturdayPlannedMeals() {
//        public LiveData<List<PlannedMeals>> getSundayPlannedMeals {
//        }
//        public LiveData<List<PlannedMeals>> getMondayPlannedMeals {
//        }
//        public LiveData<List<PlannedMeals>> getTuesdayPlannedMeals {
//        }
//        public LiveData<List<PlannedMeals>> getWednesdayPlannedMeals {
//        }
//        public LiveData<List<PlannedMeals>> getThursdayPlannedMeals {
//        }
//        public LiveData<List<PlannedMeals>> getFridayPlannedMeals {
//        }

    public void updateOrInsertMeal(List<PlannedMeals> meals, PlannedMeals storeMealAtPlan, String day) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (meals != null && !meals.isEmpty()) {
                    PlannedMeals savedMeal =
                            meals.stream().filter(meal -> storeMealAtPlan.mealId.equals(meal.getMealId())).findAny().orElse(null);
//                    Log.i(TAG, "inside first stored Planed");
                    if (savedMeal != null) {
//                        Log.i(TAG, "inside 2 stored Planed found the  meal");
                        switch (day) {
                            case "Saturday":
//                                Log.i(TAG, "inside sat");
                                mealDAO.updateSaturday(storeMealAtPlan.getMealId(), true);
                                break;
                            case "Sunday":
                                mealDAO.updateSunday(storeMealAtPlan.getMealId(), true);
                                break;
                            case "Monday":
                                mealDAO.updateMonday(storeMealAtPlan.getMealId(), true);
                                break;
                            case "Tuesday":
                                mealDAO.updateTuesday(storeMealAtPlan.getMealId(), true);
                                break;
                            case "Wednesday":
                                mealDAO.updateWednesday(storeMealAtPlan.getMealId(), true);
                                break;
                            case "Thursday":
                                mealDAO.updateThursday(storeMealAtPlan.getMealId(), true);
                                break;
                            case "Friday":
                                mealDAO.updateFriday(storeMealAtPlan.getMealId(), true);
                                break;
                        }
                    } else {
//                        Log.i(TAG, "meal not found, inserting new meal");
                        mealDAO.insert(storeMealAtPlan);
                    }
                } else {
//                    Log.i(TAG, "meal not found, inserting new meal");
                    mealDAO.insert(storeMealAtPlan);
                }
            }
        }).start();
    }

    public void updateOrDeletMeal(PlannedMeals removeMealFromDay, String day) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                switch (day) {
                    case "Saturday":
//                      Log.i(TAG, "inside sat");
                        removeMealFromDay.setSaturday(false);
                        mealDAO.updateSaturday(removeMealFromDay.getMealId(), false);
                        mealStoredAtAnyDay(removeMealFromDay);
                        break;
                    case "Sunday":
                        mealDAO.updateSunday(removeMealFromDay.getMealId(), false);
                        removeMealFromDay.setSunday(false);
                        mealStoredAtAnyDay(removeMealFromDay);
                        break;
                    case "Monday":
                        mealDAO.updateMonday(removeMealFromDay.getMealId(), false);
                        removeMealFromDay.setMonday(false);
                        mealStoredAtAnyDay(removeMealFromDay);
                        break;
                    case "Tuesday":
                        mealDAO.updateTuesday(removeMealFromDay.getMealId(), false);
                        removeMealFromDay.setTuesday(false);
                        mealStoredAtAnyDay(removeMealFromDay);
                        break;
                    case "Wednesday":
                        mealDAO.updateWednesday(removeMealFromDay.getMealId(), false);
                        removeMealFromDay.setWednesday(false);
                        mealStoredAtAnyDay(removeMealFromDay);
                        break;
                    case "Thursday":
                        mealDAO.updateThursday(removeMealFromDay.getMealId(), false);
                        removeMealFromDay.setThursday(false);
                        mealStoredAtAnyDay(removeMealFromDay);
                        break;
                    case "Friday":
                        mealDAO.updateFriday(removeMealFromDay.getMealId(), false);
                        removeMealFromDay.setFriday(false);
                        mealStoredAtAnyDay(removeMealFromDay);
                        break;
                }
            }
        }).start();
    }

    public void mealStoredAtAnyDay(PlannedMeals plannedMeal) {
        if (!plannedMeal.isFriday() && !plannedMeal.isThursday() && !plannedMeal.isSaturday() && !plannedMeal.isSunday() && !plannedMeal.isWednesday()
                && !plannedMeal.isTuesday() && ! plannedMeal.isMonday()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mealDAO.delete(plannedMeal);
                }
            }).start();
        }
    }
}
