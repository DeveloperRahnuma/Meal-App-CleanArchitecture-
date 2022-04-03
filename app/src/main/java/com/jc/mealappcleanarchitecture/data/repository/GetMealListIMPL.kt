package com.jc.mealappcleanarchitecture.data.repository

import com.jc.mealappcleanarchitecture.data.model.MealsDTO
import com.jc.mealappcleanarchitecture.data.remote.MealSearchApi
import com.jc.mealappcleanarchitecture.domain.repository.MealSearchRepository

class GetMealListIMPL(private val mealSearchApi: MealSearchApi) : MealSearchRepository {
    override suspend fun getMealList(s: String): MealsDTO {
        return mealSearchApi.getMealList(s)
    }
}