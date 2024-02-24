package com.example.foodplanner.screens.sharedMainActivity.search.Categry.View;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.data.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> implements Filterable {
    String TAG = "TAG";

    private Context context;
    private List<Category> categoryList;
    private List<Category> allcategoryList; // as original


    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
        allcategoryList =  new ArrayList<>(categoryList);// new to make new one as original
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.categoies_card, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
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

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Category> filteredList = allcategoryList.stream()
                    .filter(category -> constraint.toString().isEmpty() ||
                            category.getStrCategory().toLowerCase().contains(constraint.toString().toLowerCase()))
                    .collect(Collectors.toList());
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            categoryList.clear();
            categoryList.addAll((List<Category>) results.values);
            notifyDataSetChanged();
        }
    };


    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView imageView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tvMininCardName);
            imageView = itemView.findViewById(R.id.ImgMiniCard);

        }
    }
//
//    private List<Category> filterCategories(String letter) {
//        List<Category> filteredCategories = new ArrayList<>();
//        for (Category category : originalCategories) {
//            if (category.getStrCategory().toLowerCase().contains(letter.toLowerCase())) {
//                filteredCategories.add(category);
//            }
//        }
//        return filteredCategories;
//    }
}
