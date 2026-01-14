package com.cidead.pmdm.foodexplorer.data.repository;

import com.cidead.pmdm.foodexplorer.data.api.RetrofitClient;
import com.cidead.pmdm.foodexplorer.data.model.Country;
import com.cidead.pmdm.foodexplorer.data.model.CountryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryRepository {
    public interface CountryCallback {
        void onSuccess(List<Country> result);
        void onError(Throwable t);
    }

    public void getCountries(CountryCallback callback) {
        RetrofitClient.getApiService().getCountries().enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
