package com.example.foodplanner.screens.sharedMainActivity.plan.view;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.DayViewHolder> {
    private List<DaySchedule> daySchedules;

    // Constructor and methods to set data

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your layout for each day
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        DaySchedule daySchedule = daySchedules.get(position);
        // Bind data for each day
    }

    @Override
    public int getItemCount() {
        return daySchedules.size();
    }

    static class DayViewHolder extends RecyclerView.ViewHolder {
        // ViewHolder for each day
    }
}
