package com.example.foodplanner.screens.sharedMainActivity.search.Ingredients.View;

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
import com.example.foodplanner.data.model.MealCard;
import com.example.foodplanner.screens.Card.view.MealCardActivity;

import java.util.List;

public class SpecificIngredientMealsAdaptor extends RecyclerView.Adapter<SpecificIngredientMealsAdaptor.IngredientMealViewHolder> {

    private Context context;
    private List<MealCard> mealCards;
    String TAG = "TAG";

    public SpecificIngredientMealsAdaptor(Context context, List<MealCard> mealCards) {
        this.context = context;
        this.mealCards = mealCards;
    }

    @NonNull
    @Override
    public IngredientMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ingredient_meal, parent, false);
        return new IngredientMealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientMealViewHolder holder, int position) {
        MealCard current = mealCards.get(position);
//        Log.i(TAG, "on binding" + current.getName());
        holder.nameTextView.setText(current.getName());
        Glide.with(context).load(current.getPhotourl()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i(TAG, "on bindingggggggggggggggggggggg ing 1" + current.getIngr1());
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

    static class IngredientMealViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView imageView;

        public IngredientMealViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_ing_mealName);
            imageView = itemView.findViewById(R.id.img_ing_meal);

        }
    }

}
