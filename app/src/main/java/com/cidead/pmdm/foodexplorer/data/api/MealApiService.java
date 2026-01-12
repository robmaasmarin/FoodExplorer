package com.cidead.pmdm.foodexplorer.data.api;

import com.cidead.pmdm.foodexplorer.data.model.CountryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealApiService {

    @GET("list.php?a=list")
    Call<CountryResponse> getCountries();
}
