package com.cidead.pmdm.foodexplorer.ui.countries;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cidead.pmdm.foodexplorer.R;
import com.cidead.pmdm.foodexplorer.data.model.Country;
import com.cidead.pmdm.foodexplorer.ui.meals.MealsActivity;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<Country> countryList;

    public CountryAdapter(List<Country> countryList) {
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        // mostramos el nombre del país
        holder.tvCountryName.setText(countryList.get(position).getName());

        // al hacer clic en el país abrimos mealsactivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MealsActivity.class);
            intent.putExtra(MealsActivity.EXTRA_COUNTRY, countryList.get(position).getName());
            v.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    static class CountryViewHolder extends RecyclerView.ViewHolder {

        TextView tvCountryName;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountryName = itemView.findViewById(R.id.tvCountryName);
        }
    }
}
