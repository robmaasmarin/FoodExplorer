package com.cidead.pmdm.foodexplorer.ui.detail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.cidead.pmdm.foodexplorer.R;

public class MealDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MEAL_ID = "meal_id";

    private android.widget.ImageView ivMealImage;
    private android.widget.TextView tvMealName;
    private android.widget.TextView tvIngredients;
    private android.widget.TextView tvInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        // Inicializar views
        ivMealImage = findViewById(R.id.ivMealImage);
        tvMealName = findViewById(R.id.tvMealName);
        tvIngredients = findViewById(R.id.tvIngredients);
        tvInstructions = findViewById(R.id.tvInstructions);

        // Obtener ID del intent
        String mealId = getIntent().getStringExtra(EXTRA_MEAL_ID);
        if (mealId == null) {
            finish(); // cerrar Activity si no hay ID
            return;
        }

        MealDetailViewModel viewModel =
                new ViewModelProvider(this).get(MealDetailViewModel.class);

        viewModel.getDetail().observe(this, detail -> {
            if (detail == null) return;

            // Glide seguro
            Glide.with(this)
                    .load(detail.getImage())
                    .placeholder(R.drawable.placeholder) // drawable simple
                    .error(R.drawable.error)
                    .centerCrop()
                    .into(ivMealImage);

            tvMealName.setText(detail.getName());
            tvIngredients.setText(detail.getIngredients());
            tvInstructions.setText(detail.getInstructions());
        });

        viewModel.loadMeal(mealId);
    }
}
