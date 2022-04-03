package com.jc.mealappcleanarchitecture.presentation.mealsearch

import com.jc.mealappcleanarchitecture.domain.model.Meal

data class MealSearchState(
    val data : List<Meal>? = null,
    val error: String = "",
    val isLoading : Boolean = false,
)
