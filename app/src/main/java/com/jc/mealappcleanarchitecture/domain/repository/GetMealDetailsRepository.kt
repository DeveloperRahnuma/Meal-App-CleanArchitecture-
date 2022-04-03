package com.jc.mealappcleanarchitecture.domain.repository

import com.jc.mealappcleanarchitecture.data.model.MealsDTO

interface GetMealDetailsRepository {
    suspend fun getMealDetails( s: String) : MealsDTO
}