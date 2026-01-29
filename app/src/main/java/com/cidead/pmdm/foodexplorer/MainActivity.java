package com.cidead.pmdm.foodexplorer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cidead.pmdm.foodexplorer.data.api.RetrofitClient;
import com.cidead.pmdm.foodexplorer.data.model.Country;
import com.cidead.pmdm.foodexplorer.data.model.CountryResponse;
import com.cidead.pmdm.foodexplorer.ui.countries.CountryAdapter;
import com.cidead.pmdm.foodexplorer.ui.countries.CountryViewModel;
import com.cidead.pmdm.foodexplorer.ui.favorites.FavoritesActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Handler;
import android.os.Looper;




public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FoodExplorer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rvCountries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CountryViewModel viewModel =
                new ViewModelProvider(this).get(CountryViewModel.class);

        viewModel.getCountries().observe(this, countries -> {
            CountryAdapter adapter = new CountryAdapter(countries);
            recyclerView.setAdapter(adapter);
        });

        viewModel.loadCountries();
       /* Button btnFavorites = findViewById(R.id.btnFavorites);
        btnFavorites.setOnClickListener(v -> {
            Intent intent = new Intent(this, FavoritesActivity.class);
            startActivity(intent);
        });*/
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

    }
}