package com.example.foodplanner.screens.sharedMainActivity.search.Area.View;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
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
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.data.model.Area;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.AreaViewHolder> {
    private Context context;
    private List<Area> areaList;

    public AreaAdapter(Context context, List<Area> areaList) {
        this.context = context;
        this.areaList = areaList;
    }

    String[] countryCodes = {
            "US", "GB", "CA", "CN", "HR", "NL", "EG", "PH", "FR", "GR",
            "IN", "IE", "IT", "JM", "JP", "KE", "MY", "MX", "MA", "PL",
            "PT", "RU", "ES", "TH", "TN", "TR", "FM", "VN"
    };

    void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
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
        String countryCode = countryCodes[position];

        holder.tv_areaName.setText(current.getStrArea());
//https://flagsapi.com/US/flat/64.png
        Glide.with(context).load("https://flagsapi.com/" + countryCode + "/flat/64.png")
                .apply(new RequestOptions().override(120, 64))
                .into(holder.AreaMealImage);

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
        ImageView AreaMealImage;

        public AreaViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_areaName = itemView.findViewById(R.id.tv_areaName);
            AreaMealImage = itemView.findViewById(R.id.img_area);
        }
    }

}
