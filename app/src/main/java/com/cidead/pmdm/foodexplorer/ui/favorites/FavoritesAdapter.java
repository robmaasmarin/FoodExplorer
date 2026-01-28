package com.cidead.pmdm.foodexplorer.ui.favorites;

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
import com.cidead.pmdm.foodexplorer.data.model.FavoriteMeal;
import com.cidead.pmdm.foodexplorer.ui.detail.MealDetailActivity;
import com.cidead.pmdm.foodexplorer.utils.FavoritesManager;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavViewHolder> {

    public interface OnFavoriteRemoved {
        void onFavoriteRemoved();
    }
    private final List<FavoriteMeal> favorites;
    private final OnFavoriteRemovedListener callback;

    public interface OnFavoriteRemovedListener {
        void onFavoriteRemoved(FavoriteMeal meal);
    }

    public FavoritesAdapter(List<FavoriteMeal> favorites, OnFavoriteRemovedListener callback) {
        this.favorites = favorites;
        this.callback = callback;
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meal, parent, false);
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        FavoriteMeal meal = favorites.get(position);

        holder.tvMealName.setText(meal.getName());
        Glide.with(holder.itemView.getContext())
                .load(meal.getThumb())
                .placeholder(R.drawable.placeholder)
                .into(holder.ivMealThumb);

        // ❤️ Siempre corazón lleno en favoritos
        holder.btnFavorite.setImageResource(R.drawable.baseline_favorite_24);

        // 👉 CLICK EN CORAZÓN = eliminar favorito
        holder.btnFavorite.setOnClickListener(v -> {

            int pos = holder.getAdapterPosition();
            FavoriteMeal removed = favorites.get(pos);

            FavoritesManager.toggleFavorite(v.getContext(), removed);

            favorites.remove(pos);
            notifyItemRemoved(pos);

            if (callback != null) {
                callback.onFavoriteRemoved(removed);
            }
        });


        // 👉 Click normal abre detalle
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MealDetailActivity.class);
            intent.putExtra(MealDetailActivity.EXTRA_MEAL_ID, meal.getId());
            v.getContext().startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return favorites.size();
    }

    static class FavViewHolder extends RecyclerView.ViewHolder {
        ImageButton btnFavorite;
        TextView tvMealName;
        ImageView ivMealThumb;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMealName = itemView.findViewById(R.id.tvMealName);
            ivMealThumb = itemView.findViewById(R.id.ivMealThumb);
            btnFavorite = itemView.findViewById(R.id.btnFavoriteItem);
        }
    }
    public void updateData(List<FavoriteMeal> newFavorites) {
        favorites.clear();
        favorites.addAll(newFavorites);
        notifyDataSetChanged();
    }

}


