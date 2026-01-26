package com.cidead.pmdm.foodexplorer.ui.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    FavoritesAdapter adapterFav;

    RecyclerView rvFavorites;
    /*MealAdapter adapter;
    List<Meal> meals = new ArrayList<>();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        rvFavorites = findViewById(R.id.rvFavorites);
        rvFavorites.setLayoutManager(new LinearLayoutManager(this));
        adapterFav = new FavoritesAdapter(new ArrayList<>());
        rvFavorites.setAdapter(adapterFav);




    }
    @Override
    protected void onResume() {
        super.onResume();
        List<FavoriteMeal> favorites = FavoritesManager.getFavoriteMeals(this);
        adapterFav.updateData(favorites);

    }

}

