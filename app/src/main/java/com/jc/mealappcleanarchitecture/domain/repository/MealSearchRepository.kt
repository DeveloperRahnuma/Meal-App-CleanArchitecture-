package com.jc.mealappcleanarchitecture.domain.repository

import com.jc.mealappcleanarchitecture.data.model.MealsDTO

interface MealSearchRepository {
    suspend fun getMealList( s: String) : MealsDTO
}
