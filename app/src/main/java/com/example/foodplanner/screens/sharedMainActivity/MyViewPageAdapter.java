package com.example.foodplanner.screens.sharedMainActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.foodplanner.screens.sharedMainActivity.DailyInsperationAllMeals.MinimizedMealCard.view.DailyInspFragment;
import com.example.foodplanner.screens.sharedMainActivity.favMeals.favList.views.FragFavMeals;
import com.example.foodplanner.screens.sharedMainActivity.plan.view.PlanFragment;
import com.example.foodplanner.screens.sharedMainActivity.search.view.FragSearchView;

public class MyViewPageAdapter extends FragmentStateAdapter {
    public MyViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch (position){

           case 0:
               return new DailyInspFragment();
           case 1:
               return new FragSearchView();
           case 2:
               return new PlanFragment();
           case 3:
               return new FragFavMeals();
           default:
               return new DailyInspFragment();
       }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
