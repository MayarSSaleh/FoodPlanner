package com.example.foodplanner.screens.sharedMainActivity.basicSharedScreen.view;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.foodplanner.screens.sharedMainActivity.dailyInspirationMeals.view.DailyInspFragment;
import com.example.foodplanner.screens.sharedMainActivity.favMeals.views.FragmentFavMeals;
import com.example.foodplanner.screens.sharedMainActivity.plan.view.PlanFragment;
import com.example.foodplanner.screens.sharedMainActivity.search.mainSearchScreen.view.FragSearchView;

public class MyViewPageAdapter extends FragmentStateAdapter {
    boolean isA_Guest;
    Context context;

    public MyViewPageAdapter(@NonNull FragmentActivity fragmentActivity, boolean isguest, Context context) {
        super(fragmentActivity);
        this.isA_Guest = isguest;
        this.context = context;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new DailyInspFragment();
            case 1:
                return new FragSearchView();
            case 2:
                if (isA_Guest) {
                } else {
                    return new PlanFragment();
                }
            case 3:
                if (isA_Guest) {
                } else {
                    return new FragmentFavMeals();
                }
            default:
                return new DailyInspFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
