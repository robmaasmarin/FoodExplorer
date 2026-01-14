package com.cidead.pmdm.foodexplorer.ui.meals;

import androidx.recyclerview.widget.RecyclerView;

import com.cidead.pmdm.foodexplorer.data.model.Meal;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cidead.pmdm.foodexplorer.R;
import com.cidead.pmdm.foodexplorer.data.model.Meal;
import com.cidead.pmdm.foodexplorer.ui.detail.MealDetailActivity;

import java.util.List;

public class MealAdapter extends RecyclerView

        .Adapter<MealAdapter.MealViewHolder> {

    private List<Meal> meals;

    public MealAdapter(List<Meal> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meal, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.tvMealName.setText(meal.getName());
        Glide.with(holder.itemView.getContext())
                .load(meal.getThumb())
                .into(holder.ivMealThumb);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MealDetailActivity.class);
            intent.putExtra(MealDetailActivity.EXTRA_MEAL_ID, meal.getId());
            v.getContext().startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class MealViewHolder extends RecyclerView.ViewHolder {
        TextView tvMealName;
        ImageView ivMealThumb;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMealName = itemView.findViewById(R.id.tvMealName);
            ivMealThumb = itemView.findViewById(R.id.ivMealThumb);
        }
    }
}
