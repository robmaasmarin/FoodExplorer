package com.cidead.pmdm.foodexplorer.data.api;

import com.cidead.pmdm.foodexplorer.data.model.CountryResponse;
import com.cidead.pmdm.foodexplorer.data.model.MealDetailResponse;
import com.cidead.pmdm.foodexplorer.data.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealApiService {

    @GET("list.php?a=list")
    Call<CountryResponse> getCountries();

    @GET("filter.php")
    Call<MealResponse> getMealsByCountry(@Query("a") String country);

    @GET("lookup.php")
    Call<MealDetailResponse> getMealDetail(@Query("i") String mealId);


}
