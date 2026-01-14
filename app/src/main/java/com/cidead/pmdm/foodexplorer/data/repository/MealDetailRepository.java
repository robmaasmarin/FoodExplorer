package com.cidead.pmdm.foodexplorer.data.repository;

import com.cidead.pmdm.foodexplorer.data.api.RetrofitClient;
import com.cidead.pmdm.foodexplorer.data.model.MealDetail;
import com.cidead.pmdm.foodexplorer.data.model.MealDetailResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealDetailRepository {
    public interface DetailCallback {
        void onSuccess(MealDetail detail);
        void onError(Throwable t);
    }

    public void getMealDetail(String id, DetailCallback callback) {
        RetrofitClient.getApiService().getMealDetail(id)
                .enqueue(new Callback<MealDetailResponse>() {
                    @Override
                    public void onResponse(Call<MealDetailResponse> call,
                                           Response<MealDetailResponse> response) {
                        if (response.isSuccessful()
                                && response.body() != null
                                && response.body().getMeals() != null
                                && !response.body().getMeals().isEmpty()) {
                            callback.onSuccess(response.body().getMeals().get(0));
                        }
                    }

                    @Override
                    public void onFailure(Call<MealDetailResponse> call, Throwable t) {
                        callback.onError(t);
                    }
                });
    }
}
