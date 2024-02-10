package com.example.foodplanner.screens.sharedMainActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.foodplanner.screens.sharedMainActivity.DailyInsperationAllMeals.MinimizedMealCard.view.FragDailyInspActivity;
import com.example.foodplanner.screens.sharedMainActivity.favMeals.favList.view.FragFavMealsActivity;
import com.example.foodplanner.screens.sharedMainActivity.plan.view.FraPlanActivity;
import com.example.foodplanner.screens.sharedMainActivity.search.view.fragSearchActivity;

public class MyViewPageAdapter extends FragmentStateAdapter {
    public MyViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch (position){

           case 0:
               return new FragDailyInspActivity();
           case 1:
               return new fragSearchActivity();
           case 2:
               return new FraPlanActivity();
           case 3:
               return new FragFavMealsActivity();
           default:
               return new FragDailyInspActivity();
       }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
