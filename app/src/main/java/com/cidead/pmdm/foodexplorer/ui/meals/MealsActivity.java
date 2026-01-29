package com.cidead.pmdm.foodexplorer.ui.meals;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cidead.pmdm.foodexplorer.MainActivity;
import com.cidead.pmdm.foodexplorer.R;
import com.cidead.pmdm.foodexplorer.data.api.RetrofitClient;
import com.cidead.pmdm.foodexplorer.data.model.Meal;
import com.cidead.pmdm.foodexplorer.data.model.MealResponse;
import com.cidead.pmdm.foodexplorer.ui.favorites.FavoritesActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    public static final String EXTRA_COUNTRY = "country";
    private static final String TAG = "MealsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);

        recyclerView = findViewById(R.id.rvMeals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String country = getIntent().getStringExtra(EXTRA_COUNTRY);
        Log.d("MealsActivity", "Country received: " + country);

        MealViewModel viewModel =
                new ViewModelProvider(this).get(MealViewModel.class);

        viewModel.getMeals().observe(this, meals -> {
            MealAdapter adapter = new MealAdapter(meals);
            recyclerView.setAdapter(adapter);

        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            } else if (id == R.id.nav_favorites) {
                Intent intent = new Intent(this, FavoritesActivity.class);
                startActivity(intent);
                return true;
            }

            return false;
        });


viewModel.loadMeals(country);



    }
    @Override
    protected void onResume() {
        super.onResume();
        if (recyclerView.getAdapter() != null) {
            recyclerView.getAdapter().notifyDataSetChanged();
        }    }

}
