package com.cidead.pmdm.foodexplorer.ui.countries;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cidead.pmdm.foodexplorer.data.model.Country;
import com.cidead.pmdm.foodexplorer.data.repository.CountryRepository;

import java.util.List;

public class CountryViewModel extends ViewModel {

    private final CountryRepository repository;
    private final MutableLiveData<List<Country>> countries = new MutableLiveData<>();

    public CountryViewModel() {
        repository = new CountryRepository();
    }

    public LiveData<List<Country>> getCountries() {
        return countries;
    }

    public void loadCountries() {
        repository.getCountries(new CountryRepository.CountryCallback() {
            @Override
            public void onSuccess(List<Country> result) {
                countries.setValue(result);
            }

            @Override
            public void onError(Throwable t) {
                // más adelante podemos manejar errores
            }
        });
    }
}