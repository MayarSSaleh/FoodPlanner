package com.example.foodplanner.screens.sharedMainActivity.search.Area.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.data.model.Area;

import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.CategoryViewHolder>  {
    final String TAG = "TAG";

    private Context context;
    private List<Area> areaList;

    public AreaAdapter(Context context, List<Area> areaList) {
        this.context = context;
        this.areaList = areaList;
    }

    void setList(List<Area> areaList) {
        this.areaList = areaList;
    }
   @NonNull
    @Override
    public AreaAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.area_name, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaAdapter.CategoryViewHolder holder, int position) {
        Area current = areaList.get(position);
        holder.tv_areaName.setText(current.getStrArea());
        holder.tv_areaName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // go to country meals
            }
        });
    }

    @Override
    public int getItemCount() {
        return areaList.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tv_areaName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_areaName = itemView.findViewById(R.id.tv_areaName);
        }
    }
}

