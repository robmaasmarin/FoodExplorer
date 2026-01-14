package com.cidead.pmdm.foodexplorer.ui.meals;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cidead.pmdm.foodexplorer.R;
import com.cidead.pmdm.foodexplorer.data.api.RetrofitClient;
import com.cidead.pmdm.foodexplorer.data.model.Meal;
import com.cidead.pmdm.foodexplorer.data.model.MealResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealsActivity extends AppCompatActivity {

    public static final String EXTRA_COUNTRY = "country";
    private static final String TAG = "MealsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);

        RecyclerView recyclerView = findViewById(R.id.rvMeals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String country = getIntent().getStringExtra(EXTRA_COUNTRY);
        Log.d("MealsActivity", "Country received: " + country);
        MealViewModel viewModel =
                new ViewModelProvider(this).get(MealViewModel.class);

        viewModel.getMeals().observe(this, meals -> {
            MealAdapter adapter = new MealAdapter(meals);
            recyclerView.setAdapter(adapter);
        });

viewModel.loadMeals(country);


    }
}
