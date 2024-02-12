package com.example.foodplanner.screens.sharedMainActivity.plan.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodplanner.R;


public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.DaysViewHolder> {
    minimized_planned_meal_card.xml
    recycle view of>>>>>>>>day_layout_

    @NonNull
    @Override
    public DaysAdapter.DaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DaysAdapter.DaysViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    static class DaysViewHolder extends RecyclerView.ViewHolder{
       TextView day;
       RecyclerView recyclerView;

        public DaysViewHolder(@NonNull View itemView) {
            super(itemView);
            day=itemView.findViewById(R.id.tv_day);
            recyclerView=itemView.findViewById(R.id.mealsRecyclerViewInPlan);
        }

    }
}
