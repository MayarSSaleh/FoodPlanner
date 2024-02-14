package com.example.foodplanner.screens.Card.view;

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
import com.example.foodplanner.data.model.Ingredient;

import java.util.Collections;
import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private static final String TAG = "team";
    Context context;
    List<Ingredient> ingredientList = Collections.emptyList();
    private String base ="https://www.themealdb.com/images/ingredients/";
    private  String remainAccordingIng =".png";

    public IngredientsAdapter(Context context) {
        this.context = context;
    }

    public void setIngredientsList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.ingredient, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient current = ingredientList.get(position);
        holder.ingName.setText(current.getName());
        holder.ingAmount.setText(current.getAmount());
        Glide.with(context).load(base+current.getName()+remainAccordingIng).into(holder.ingImag);
    }

    @Override
    public int getItemCount()
    {
        return ingredientList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingName;
        TextView ingAmount;
        ImageView ingImag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingName = itemView.findViewById(R.id.tv_ing_amount);
            ingAmount = itemView.findViewById(R.id.tv_ingName);
            ingImag = itemView.findViewById(R.id.ingImag);
        }
    }

}

