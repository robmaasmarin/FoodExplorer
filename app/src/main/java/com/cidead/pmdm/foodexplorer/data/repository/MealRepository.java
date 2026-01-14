package com.cidead.pmdm.foodexplorer.data.repository;

import com.cidead.pmdm.foodexplorer.data.api.RetrofitClient;
import com.cidead.pmdm.foodexplorer.data.model.Meal;
import com.cidead.pmdm.foodexplorer.data.model.MealResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealRepository {

    public interface MealCallback {
        void onSuccess(List<Meal> meals);
        void onError(Throwable t);
    }

    public void getMealsByCountry(String country, MealCallback callback) {
        RetrofitClient.getApiService().getMealsByCountry(country)
                .enqueue(new Callback<MealResponse>() {
                    @Override
                    public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            callback.onSuccess(response.body().getMeals());
                        }
                    }

                    @Override
                    public void onFailure(Call<MealResponse> call, Throwable t) {
                        callback.onError(t);
                    }
                });
    }
}
