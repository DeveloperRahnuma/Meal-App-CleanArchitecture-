package com.jc.mealappcleanarchitecture.presentation.mealdetails

import com.jc.mealappcleanarchitecture.domain.model.MealDetails

data class MealsDetailsStatus(
    val data: MealDetails ? = null,
    val isLoading : Boolean = false,
    val error: String = ""
)
