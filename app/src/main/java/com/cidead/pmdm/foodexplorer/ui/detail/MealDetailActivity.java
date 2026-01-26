package com.cidead.pmdm.foodexplorer.ui.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.cidead.pmdm.foodexplorer.R;
import com.cidead.pmdm.foodexplorer.data.model.FavoriteMeal;
import com.cidead.pmdm.foodexplorer.utils.FavoritesManager;

public class MealDetailActivity extends AppCompatActivity {

    ImageButton btnFavorite;
    boolean isFavorite = false;

    ProgressBar progressBar;
    LinearLayout contentLayout;
    public static final String EXTRA_MEAL_ID = "meal_id";

    private android.widget.ImageView ivMealImage;
    private android.widget.TextView tvMealName;
    private android.widget.TextView tvIngredients;
    private android.widget.TextView tvInstructions;

    // ✅ Guardamos la URL del detalle
    private String mealImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);

        progressBar = findViewById(R.id.progressBar);
        contentLayout = findViewById(R.id.contentLayout);
        btnFavorite = findViewById(R.id.btnFavorite);

        progressBar.setVisibility(View.VISIBLE);
        contentLayout.setVisibility(View.GONE);

        ivMealImage = findViewById(R.id.ivMealImage);
        tvMealName = findViewById(R.id.tvMealName);
        tvIngredients = findViewById(R.id.tvIngredients);
        tvInstructions = findViewById(R.id.tvInstructions);

        String mealId = getIntent().getStringExtra(EXTRA_MEAL_ID);
        if (mealId == null) {
            finish();
            return;
        }

        isFavorite = FavoritesManager.isFavorite(this, mealId);
        updateFavoriteIcon();

        btnFavorite.setOnClickListener(v -> {
            // ✅ Usamos la variable mealImageUrl en lugar de getTag()
            FavoriteMeal favoriteMeal = new FavoriteMeal(
                    mealId,
                    tvMealName.getText().toString(),
                    mealImageUrl
            );

            FavoritesManager.toggleFavorite(this, favoriteMeal);
            isFavorite = !isFavorite;
            updateFavoriteIcon();
        });

        MealDetailViewModel viewModel =
                new ViewModelProvider(this).get(MealDetailViewModel.class);

        viewModel.getDetail().observe(this, detail -> {
            if (detail == null) return;

            progressBar.setVisibility(View.GONE);
            contentLayout.setVisibility(View.VISIBLE);

            // Guardamos la URL en la variable
            mealImageUrl = detail.getImage();

            Glide.with(this)
                    .load(mealImageUrl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .centerCrop()
                    .into(ivMealImage);
            ivMealImage.setTag(detail.getImage());
            tvMealName.setText(detail.getName());
            tvIngredients.setText(detail.getIngredients());
            tvInstructions.setText(detail.getInstructions());
        });

        viewModel.loadMeal(mealId);
    }

    private void updateFavoriteIcon() {
        btnFavorite.setImageResource(
                isFavorite
                        ? R.drawable.baseline_favorite_24
                        : R.drawable.baseline_local_police_24
        );
    }
}

