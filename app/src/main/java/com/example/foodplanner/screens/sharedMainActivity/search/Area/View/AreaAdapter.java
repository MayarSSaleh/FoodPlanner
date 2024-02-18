package com.example.foodplanner.screens.sharedMainActivity.search.Area.View;
import android.content.Context;
import android.content.Intent;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.AreaViewHolder> implements Filterable {

    private Context context;
    private List<Area> areaList;
    private List<Area> allAreaList;// as original

    public AreaAdapter(Context context, List<Area> areaList) {
        this.context = context;
        this.areaList = areaList;
        this.allAreaList = new ArrayList<>(areaList);
    }

    @NonNull
    @Override
    public AreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.area_name, parent, false);
        return new AreaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaViewHolder holder, int position) {

        Area current = areaList.get(position);
        holder.tv_areaName.setText(current.getStrArea());
        holder.tv_areaName.setOnClickListener(v -> {
            Intent intent = new Intent(context, AreaMealsActivity.class);
            intent.putExtra("areaName", current.getStrArea());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return areaList.size();
    }

    static class AreaViewHolder extends RecyclerView.ViewHolder {
        TextView tv_areaName;

        public AreaViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_areaName = itemView.findViewById(R.id.tv_areaName);
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Area> filteredList = allAreaList.stream()
//                     area.getStrArea()
                    .filter(area -> constraint.toString().isEmpty() ||
                            area.getStrArea().toLowerCase().contains(constraint.toString().toLowerCase()))
                    .collect(Collectors.toList());
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            areaList.clear();
            areaList.addAll((List<Area>) results.values);
            notifyDataSetChanged();
        }
    };
}
