package com.jc.mealappcleanarchitecture.presentation.mealdetails

import androidx.lifecycle.ViewModel
import com.jc.mealappcleanarchitecture.common.Resource
import com.jc.mealappcleanarchitecture.domain.usecase.GetMealDetailsUseCase
import com.jc.mealappcleanarchitecture.domain.usecase.GetMealSearchListUseCase
import com.jc.mealappcleanarchitecture.presentation.mealsearch.MealSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(private val getMealDetailsUseCase: GetMealDetailsUseCase) : ViewModel() {
    private val _mealsDetails = MutableStateFlow<MealsDetailsStatus>(MealsDetailsStatus())
    val mealsDetails : StateFlow<MealsDetailsStatus> = _mealsDetails


    fun getMealDetails(id : String){
        getMealDetailsUseCase(id).onEach {
            when(it){
                is Resource.Loading -> {
                    _mealsDetails.value = MealsDetailsStatus(isLoading = true)
                }
                is Resource.Error -> {
                    _mealsDetails.value = MealsDetailsStatus(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _mealsDetails.value = MealsDetailsStatus(data = it.data)
                }
            }
        }
    }
}