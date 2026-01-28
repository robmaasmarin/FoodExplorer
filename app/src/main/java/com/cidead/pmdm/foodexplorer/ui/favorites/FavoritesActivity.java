package com.cidead.pmdm.foodexplorer.ui.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cidead.pmdm.foodexplorer.R;
import com.cidead.pmdm.foodexplorer.data.model.FavoriteMeal;
import com.cidead.pmdm.foodexplorer.data.model.Meal;
import com.cidead.pmdm.foodexplorer.ui.detail.MealDetailActivity;
import com.cidead.pmdm.foodexplorer.ui.meals.MealAdapter;
import com.cidead.pmdm.foodexplorer.utils.FavoritesManager;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    FavoritesAdapter adapterFav;
    //mensaje si no tenemos favoritos
    TextView tvEmptyFavorites;

    RecyclerView rvFavorites;
    /*MealAdapter adapter;
    List<Meal> meals = new ArrayList<>();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        rvFavorites = findViewById(R.id.rvFavorites);
        rvFavorites.setLayoutManager(new LinearLayoutManager(this));
        adapterFav = new FavoritesAdapter(
                new ArrayList<>(),
                removedMeal -> {

                    Snackbar.make(
                            rvFavorites,
                            "Eliminado de favoritos",
                            Snackbar.LENGTH_LONG
                    ).setAction("DESHACER", v -> {

                        FavoritesManager.toggleFavorite(this, removedMeal);
                        //llamamos a refresh ui para mostrar mensaje de que no tenemos favoritos
                        refreshUI();

                    }).show();

                    refreshUI();

                }
        );

        rvFavorites.setAdapter(adapterFav);
        tvEmptyFavorites = findViewById(R.id.tvEmptyFavorites);




    }
    @Override
    protected void onResume() {
        super.onResume();
        refreshUI();
    }

    private void refreshUI() {
        List<FavoriteMeal> favorites = FavoritesManager.getFavoriteMeals(this);
        adapterFav.updateData(favorites);

        if (favorites.isEmpty()) {
            tvEmptyFavorites.setVisibility(View.VISIBLE);
            rvFavorites.setVisibility(View.GONE);
        } else {
            tvEmptyFavorites.setVisibility(View.GONE);
            rvFavorites.setVisibility(View.VISIBLE);
        }
    }



}

