package com.example.foodplanner.screens.sharedMainActivity.search.Area.View;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.screens.Card.view.MealCardActivity;
import java.util.List;

public class AreaMealsAdapter extends RecyclerView.Adapter<AreaMealsAdapter.AreaViewHolder> {

    private Context context;
    private List<Meal> mealCards;

    public AreaMealsAdapter(Context context, List<Meal> mealCards) {
        this.context = context;
        this.mealCards = mealCards;
    }

    @NonNull
    @Override
    public AreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_by_search, parent, false);
        return new AreaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaMealsAdapter.AreaViewHolder holder, int position) {
        Meal current = mealCards.get(position);
        holder.nameTextView.setText(current.getName());
        Glide.with(context).load(current.getPhotourl()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MealCardActivity.class);
                intent.putExtra("mealName", current.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealCards.size();
    }

    static class AreaViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView imageView;

        public AreaViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_meal_name);
            imageView = itemView.findViewById(R.id.meal_img);

        }
    }

}
