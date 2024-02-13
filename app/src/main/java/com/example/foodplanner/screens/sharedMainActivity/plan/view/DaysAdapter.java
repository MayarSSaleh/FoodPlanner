package com.example.foodplanner.screens.sharedMainActivity.plan.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.data.local_db.plannedMeals.PlannedMeals;
import com.example.foodplanner.screens.Card.view.MealCardActivity;
import com.example.foodplanner.screens.sharedMainActivity.plan.presenter.PlanMealsPresenterImp;
import java.util.List;


public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.DaysViewHolder> {
    Context context;
    List<PlannedMeals> plannedMealsforAday;
    String day;
    String TAG = "a";
    public static final String PLANNED_MEAL = "planned_meal";


    public DaysAdapter(Context context, List<PlannedMeals> plannedMealsforAday, String day) {
        this.context = context;
        this.plannedMealsforAday = plannedMealsforAday;
        this.day = day;
//        Log.i(TAG, "plannedMealsforAday.size(): "+ plannedMealsforAday.size());
    }

    @NonNull
    @Override
    public DaysAdapter.DaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.minimized_planned_meal_card, parent, false);
        return new DaysAdapter.DaysViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysAdapter.DaysViewHolder holder, int position) {
        PlannedMeals current = plannedMealsforAday.get(position);
        holder.mealName.setText(current.getName());
        Glide.with(context).load(current.getPhotourl()).into(holder.mealImage);
        holder.mealImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MealCardActivity.class);
                intent.putExtra(PLANNED_MEAL, current);
                context.startActivity(intent);
            }
        });
        holder.removeFromPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: remove");
                new PlanMealsPresenterImp().removeFromPlan(current, day, context);
            }
        }
        );
//        Log.i(TAG, "onBindViewHolder: "+ current.getName() + current.getPhotourl());
    }

    @Override
    public int getItemCount() {
        return plannedMealsforAday.size();
    }

    static class DaysViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImage;
        TextView mealName;
        Button removeFromPlan;

        public DaysViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.ImgMiniMeal);
            mealName = itemView.findViewById(R.id.tvMininMealName);
            removeFromPlan = itemView.findViewById(R.id.img_remove);
        }

    }
}
