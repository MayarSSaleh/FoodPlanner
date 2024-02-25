package com.example.foodplanner.screens.sharedMainActivity.plan.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
    List<PlannedMeals> plannedMealsForA_Day;
    String day;
    public static final String PLANNED_MEAL = "planned_meal";


    public DaysAdapter(Context context, List<PlannedMeals> plannedMealsforAday, String day) {
        this.context = context;
        this.plannedMealsForA_Day = plannedMealsforAday;
        this.day = day;
    }

    @NonNull
    @Override
    public DaysAdapter.DaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.minimized_planned_meal_card, parent, false);
        return new DaysAdapter.DaysViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysAdapter.DaysViewHolder holder, int position) {
        PlannedMeals current = plannedMealsForA_Day.get(position);
         holder.mealName.setText(current.getPlannedMeal().getName());
        Glide.with(context).load(current.getPlannedMeal().getPhotourl()).into(holder.mealImage);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Hi Chef")
                        .setMessage("Remove the meal from "+ day + " plan?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new PlanMealsPresenterImp().removeFromPlan(current, day, context);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
        );
    }

    @Override
    public int getItemCount() {
        return plannedMealsForA_Day.size();
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
