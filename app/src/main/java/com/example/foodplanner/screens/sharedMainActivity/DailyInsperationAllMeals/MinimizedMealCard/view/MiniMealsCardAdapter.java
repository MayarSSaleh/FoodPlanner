package com.example.foodplanner.screens.sharedMainActivity.DailyInsperationAllMeals.MinimizedMealCard.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.MinimizedMealCardDRAFTneedUpdateWIthNormalCardAndChosseWhatToSHOW;

import java.util.List;

public class MiniMealsCardAdapter extends RecyclerView.Adapter<MiniMealsCardAdapter.MealsViewHolder> {
    private Context context;
    private List<MinimizedMealCardDRAFTneedUpdateWIthNormalCardAndChosseWhatToSHOW> mealsList;

    public MiniMealsCardAdapter(Context context, List<MinimizedMealCardDRAFTneedUpdateWIthNormalCardAndChosseWhatToSHOW> mealsList) {
        this.context = context;
        this.mealsList = mealsList;
    }

    @NonNull
    @Override
    public MealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.minminzed_card, parent, false);
        return new MealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsViewHolder holder, int position) {
        MinimizedMealCardDRAFTneedUpdateWIthNormalCardAndChosseWhatToSHOW current = mealsList.get(position);
        holder.nameTextView.setText(current.getName());
        Glide.with(context).load(current.getUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mealsList.size();
    }

    static class MealsViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView imageView;

        MealsViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tvMininMealName);
            imageView = itemView.findViewById(R.id.ImgMiniMeal);
        }
    }
}
