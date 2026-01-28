package com.cidead.pmdm.foodexplorer.ui.meals;

import androidx.recyclerview.widget.RecyclerView;

import com.cidead.pmdm.foodexplorer.data.model.FavoriteMeal;
import com.cidead.pmdm.foodexplorer.data.model.Meal;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cidead.pmdm.foodexplorer.R;
import com.cidead.pmdm.foodexplorer.data.model.Meal;
import com.cidead.pmdm.foodexplorer.ui.detail.MealDetailActivity;
import com.cidead.pmdm.foodexplorer.utils.FavoritesManager;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {


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
        // estado inicial icono favorito
        boolean isFavorite = FavoritesManager.isFavorite(
                holder.itemView.getContext(),
                meal.getId()
        );

        holder.btnFavorite.setImageResource(
                isFavorite
                        ? R.drawable.baseline_favorite_24
                        : R.drawable.outline_favorite_24
        );

        holder.tvMealName.setText(meal.getName());
        Glide.with(holder.itemView.getContext())
                .load(meal.getThumb())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.ivMealThumb);





        // añadimos acción icono favorito
        holder.btnFavorite.setOnClickListener(v -> {

            FavoriteMeal fav = new FavoriteMeal(
                    meal.getId(),
                    meal.getName(),
                    meal.getThumb()
            );


            boolean wasFavorite = FavoritesManager.isFavorite(
                    v.getContext(),
                    meal.getId()
            );
            FavoritesManager.toggleFavorite(v.getContext(), fav);

            boolean nowFavorite = !wasFavorite;


            holder.btnFavorite.setImageResource(
                    nowFavorite
                            ? R.drawable.baseline_favorite_24
                            : R.drawable.outline_favorite_24
            );

            if (nowFavorite) {
                Snackbar.make(
                        v,
                        "❤️ Añadido a favoritos",
                        Snackbar.LENGTH_SHORT
                ).show();
            } else {
                Snackbar.make(
                        v,
                        "💔 Eliminado de favoritos",
                        Snackbar.LENGTH_LONG
                ).setAction("DESHACER", undo -> {
                    FavoritesManager.toggleFavorite(v.getContext(), fav);
                    holder.btnFavorite.setImageResource(
                            R.drawable.baseline_favorite_24
                    );
                }).show();
            }

        });
        holder.btnFavorite.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(150)
                .withEndAction(() ->
                        holder.btnFavorite.animate()
                                .scaleX(1f)
                                .scaleY(1f)
                                .setDuration(150)
                                .start()
                )
                .start();
        holder.btnFavorite.setEnabled(false);

        holder.btnFavorite.postDelayed(
                () -> holder.btnFavorite.setEnabled(true),
                300
        );


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
        ImageButton btnFavorite;
        TextView tvMealName;
        ImageView ivMealThumb;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMealName = itemView.findViewById(R.id.tvMealName);
            ivMealThumb = itemView.findViewById(R.id.ivMealThumb);
            btnFavorite = itemView.findViewById(R.id.btnFavoriteItem);
        }
    }
}
