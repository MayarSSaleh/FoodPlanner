package com.example.foodplanner.screens.Card.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodplanner.R;
import com.example.foodplanner.data.model.Ingredient;
import java.util.List;

public class IngtedientsAdaptor extends RecyclerView.Adapter<IngtedientsAdaptor.ViewHolder> {

    private static final String TAG = "team";
    Context context;
    List<Ingredient> ingredientList;

    public IngtedientsAdaptor(Context context, List<Ingredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public IngtedientsAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.ingredient, parent, false);
        IngtedientsAdaptor.ViewHolder myViewHolder = new IngtedientsAdaptor.ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngtedientsAdaptor.ViewHolder holder, int position) {
        Ingredient current = ingredientList.get(position);
        holder.ingName.setText((current.getName()));
        holder.ingAmount.setText(current.getAmount());
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingName;
        TextView ingAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingName = itemView.findViewById(R.id.tv_ingName);
            ingAmount = itemView.findViewById(R.id.tv_ing_amount);
        }
    }

}

