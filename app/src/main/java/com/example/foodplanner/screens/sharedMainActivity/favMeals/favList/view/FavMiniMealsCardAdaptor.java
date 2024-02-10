package com.example.foodplanner.screens.sharedMainActivity.favMeals.favList.view;

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
import com.example.foodplanner.data.local_db.favMeals.FavMeals;
import com.example.foodplanner.data.model.MealCard;

import java.util.Collections;
import java.util.List;

public class FavMiniMealsCardAdaptor extends RecyclerView.Adapter<FavMiniMealsCardAdaptor.MealsViewHolder> {
    private Context context;
    private  List<FavMeals> favMealsList;
    public FavMiniMealsCardAdaptor(Context context, List<FavMeals> favMeals) {
        this.context = context;
        favMealsList= favMeals;
    }
    public void setList(List<FavMeals> updatedProdcuts){
        favMealsList=updatedProdcuts;

        notifyDataSetChanged();// it is importent as make the screen updated
    }
    @NonNull
    @Override
    public MealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.minminzed_card, parent, false);
        return new MealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsViewHolder holder, int position) {
        FavMeals current = favMealsList.get(position);
        holder.nameTextView.setText(current.getName());
        Glide.with(context).load(current.getPhotourl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return favMealsList.size();
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
