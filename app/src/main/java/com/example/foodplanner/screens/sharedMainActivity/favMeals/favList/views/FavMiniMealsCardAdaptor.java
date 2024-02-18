package com.example.foodplanner.screens.sharedMainActivity.favMeals.favList.views;

import static com.example.foodplanner.screens.Card.view.MealCardActivity.FAV_OBJECT;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.example.foodplanner.screens.Card.view.MealCardActivity;

import java.util.List;

public class FavMiniMealsCardAdaptor extends RecyclerView.Adapter<FavMiniMealsCardAdaptor.MealsViewHolder> {
    private Context context;
    private List<FavMeals> favMealsList;
    String TAG = "TAG";

    public FavMiniMealsCardAdaptor(Context context, List<FavMeals> favMeals) {
        this.context = context;
        favMealsList = favMeals;
    }

    public void setList(List<FavMeals> updatedProdcuts) {
        favMealsList = updatedProdcuts;
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
        // the follwoing for testing
        Glide.with(context).load(current.getPhotourl()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i(TAG, "on binding" + current);
//                Log.i(TAG, "on bindingwith" + current.getName());
                Intent intent = new Intent(context, MealCardActivity.class);
                intent.putExtra(FAV_OBJECT, current);
                context.startActivity(intent);
            }
        });
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
