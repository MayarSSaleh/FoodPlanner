package com.example.foodplanner.screens.sharedMainActivity.plan.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.DaysViewHolder>  {
    day_layout
    plan_recyclerView;

    @NonNull
    @Override
    public DaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DaysViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class DaysViewHolder extends RecyclerView.ViewHolder{


        public DaysViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
