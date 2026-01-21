package com.cidead.pmdm.foodexplorer.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cidead.pmdm.foodexplorer.data.model.MealDetail;
import com.cidead.pmdm.foodexplorer.data.repository.MealDetailRepository;

public class MealDetailViewModel extends ViewModel {
    //manejo de errores
    public enum UiState {
        LOADING,
        SUCCESS,
        ERROR
    }
    private final MealDetailRepository repository;
    private final MutableLiveData<MealDetail> detail = new MutableLiveData<>();
    private final MutableLiveData<UiState> uiState = new MutableLiveData<>();

    public MealDetailViewModel() {
        repository = new MealDetailRepository();
    }

    public LiveData<MealDetail> getDetail() {
        return detail;
    }
    public LiveData<UiState> getUiState() {
        return uiState;
    }

    public void loadMeal(String id) {
        repository.getMealDetail(id, new MealDetailRepository.DetailCallback() {
            @Override
            public void onSuccess(MealDetail result) {

                detail.setValue(result);
                uiState.setValue(UiState.SUCCESS);
            }

            @Override
            public void onError(Throwable t) {
                uiState.setValue(UiState.ERROR);
            }
        });
    }




}
