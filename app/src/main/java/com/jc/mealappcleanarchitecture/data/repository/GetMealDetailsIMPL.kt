package com.jc.mealappcleanarchitecture.data.repository

import com.jc.mealappcleanarchitecture.data.model.MealsDTO
import com.jc.mealappcleanarchitecture.data.remote.MealSearchApi
import com.jc.mealappcleanarchitecture.domain.repository.GetMealDetailsRepository

class GetMealDetailsIMPL(private val mealSearchApi: MealSearchApi) : GetMealDetailsRepository {
    override suspend fun getMealDetails(s: String): MealsDTO {
        return mealSearchApi.getMealDetail(s)
    }
}