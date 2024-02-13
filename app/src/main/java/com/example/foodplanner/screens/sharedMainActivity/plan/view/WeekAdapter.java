package com.example.foodplanner.screens.sharedMainActivity.plan.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodplanner.R;
import java.util.List;

//list of models(day, list of meals)
public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.DaysViewHolder> {
    Context context;
    List<DayMeals> dayMeals;
    DaysAdapter daysAdapter;
    String TAG = "a";

    public WeekAdapter(Context context, List<DayMeals> dayMeals) {
        this.context = context;
        this.dayMeals = dayMeals;
//        Log.i(TAG, "WeekAdapter: " + dayMeals.size());
    }

    @NonNull
    @Override
    public DaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.day_layout, parent, false);
        return new WeekAdapter.DaysViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysViewHolder holder, int position) {
        DayMeals current = dayMeals.get(position);
        holder.day.setText(current.getDay());
//        Log.i(TAG, "WeekAdapter: " + current.getListOfMeals()+ current.getDay() );

        daysAdapter = new DaysAdapter(context, current.getListOfMeals(), current.getDay());
        holder.recyclerView.setAdapter(daysAdapter);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));

    }

    @Override
    public int getItemCount() {
        return dayMeals.size();
    }

    static class DaysViewHolder extends RecyclerView.ViewHolder {
        TextView day;
        RecyclerView recyclerView;

        public DaysViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.tv_day);
            recyclerView = itemView.findViewById(R.id.mealsRecyclerViewInPlan);
        }
    }
}
