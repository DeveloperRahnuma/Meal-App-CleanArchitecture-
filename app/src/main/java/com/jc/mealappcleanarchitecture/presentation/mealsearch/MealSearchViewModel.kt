package com.jc.mealappcleanarchitecture.presentation.mealsearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jc.mealappcleanarchitecture.common.Resource
import com.jc.mealappcleanarchitecture.domain.usecase.GetMealSearchListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MealSearchViewModel @Inject constructor(private val getMealSearchListUseCase: GetMealSearchListUseCase) : ViewModel() {
    private val _mealSearchList = MutableStateFlow<MealSearchState>(MealSearchState())
    val mealSearchList : StateFlow<MealSearchState> = _mealSearchList

    fun searchMealList(s : String){
        try {
            getMealSearchListUseCase(s).onEach {
                when(it){
                    is Resource.Loading -> {
                        _mealSearchList.value = MealSearchState(isLoading = true)
                    }
                    is Resource.Error -> {
                        _mealSearchList.value = MealSearchState(error = it.message ?: "")
                    }
                    is Resource.Success -> {
                        _mealSearchList.value = MealSearchState(data = it.data)
                    }
                }
            }.launchIn(viewModelScope)
        }catch (e : Exception){
            var a = e.message
            e.printStackTrace()
        }
    }
}