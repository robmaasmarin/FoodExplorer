package com.cidead.pmdm.foodexplorer;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cidead.pmdm.foodexplorer.data.api.RetrofitClient;
import com.cidead.pmdm.foodexplorer.data.model.Country;
import com.cidead.pmdm.foodexplorer.data.model.CountryResponse;
import com.cidead.pmdm.foodexplorer.ui.countries.CountryAdapter;
import com.cidead.pmdm.foodexplorer.ui.countries.CountryViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FoodExplorer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    }
}