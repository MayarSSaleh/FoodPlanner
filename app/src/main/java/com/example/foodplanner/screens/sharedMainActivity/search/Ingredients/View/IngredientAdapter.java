package com.example.foodplanner.screens.sharedMainActivity.search.Ingredients.View;


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
import com.example.foodplanner.data.model.Category;
import com.example.foodplanner.screens.sharedMainActivity.search.Categry.Presenter.CategoryPresenterImp;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    String TAG = "TAG";

    private Context context;
    private List<Category> categoryList;
    private List<String> writenCategory;
    CategoryPresenterImp categoryPresenterImp;


    public IngredientAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    void setList(List<Category> newcategoryList) {
        categoryList = newcategoryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.categoies_card, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Category current = categoryList.get(position);
        Log.i(TAG, "on binding" + current.getStrCategory());
        holder.nameTextView.setText(current.getStrCategory());
        Glide.with(context).load(current.getStrCategoryThumb()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CategoryMealsActivity.class);
                intent.putExtra("categoryName", current.getStrCategory());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    static class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView imageView;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tvMininCardName);
            imageView = itemView.findViewById(R.id.ImgMiniCard);

        }
    }

}
