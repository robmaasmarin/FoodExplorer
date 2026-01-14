package com.cidead.pmdm.foodexplorer.ui.meals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cidead.pmdm.foodexplorer.data.model.Meal;
import com.cidead.pmdm.foodexplorer.data.repository.MealRepository;

import java.util.List;

public class MealViewModel extends ViewModel {

    private final MealRepository repository;
    private final MutableLiveData<List<Meal>> meals = new MutableLiveData<>();

    public MealViewModel() {
        repository = new MealRepository();
    }

    public LiveData<List<Meal>> getMeals() {
        return meals;
    }

    public void loadMeals(String country) {
        repository.getMealsByCountry(country, new MealRepository.MealCallback() {
            @Override
            public void onSuccess(List<Meal> result) {
                meals.setValue(result);
            }

            @Override
            public void onError(Throwable t) {
            }
        });
    }
}
